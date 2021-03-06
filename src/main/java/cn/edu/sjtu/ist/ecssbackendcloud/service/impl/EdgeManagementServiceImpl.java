package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.EdgeInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.dao.ModelInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ModelInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.EdgeInfoUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class EdgeManagementServiceImpl implements EdgeManagementService {

    @Autowired
    private EdgeInfoUtil edgeInfoUtil;

    @Autowired
    private EdgeInfoDao edgeInfoDao;

    @Autowired
    private ModelInfoDao modelInfoDao;

    @Override
    public List<EdgeInfoDTO> getAllEdgeInfoByUser(String userId) {
        List<EdgeInfoPO> edgeInfoPOList = edgeInfoDao.findEdgeInfoPOSByOwner(userId);
        List<EdgeInfoDTO> edgeInfoDTOList = new ArrayList<>();
        for(EdgeInfoPO edgeInfoPO: edgeInfoPOList) {
            edgeInfoDTOList.add(edgeInfoUtil.convertPO2DTO(edgeInfoPO));
        }
        return edgeInfoDTOList;
    }

    @Override
    public List<EdgeInfoDTO> getAllEdgeInfo() {
        List<EdgeInfoPO> edgeInfoPOList = edgeInfoDao.findAll();
        List<EdgeInfoDTO> edgeInfoDTOList = new ArrayList<>();
        for(EdgeInfoPO edgeInfoPO: edgeInfoPOList) {
            edgeInfoDTOList.add(edgeInfoUtil.convertPO2DTO(edgeInfoPO));
        }
        return edgeInfoDTOList;
    }

    @Override
    public EdgeInfoDTO getEdgeInfoById(String EdgeId) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(EdgeId);
        if (edgeInfoPO != null) {
            return edgeInfoUtil.convertPO2DTO(edgeInfoPO);
        } else {
            throw new RuntimeException("????????????????????????");
        }
    }

    @Override
    public void deleteEdgeInfoById(String EdgeId) {
        if (edgeInfoDao.findEdgeInfoPOById(EdgeId) == null) {
            throw new RuntimeException("???????????????????????????");
        }
        edgeInfoDao.deleteEdgeInfoPOById(EdgeId);

        List<ModelInfoPO> modelInfoPOS = modelInfoDao.findAll();
        for (ModelInfoPO po : modelInfoPOS) {
            po.getEdgeIdList().remove(EdgeId);
            modelInfoDao.save(po);
        }
    }

    @Override
    public EdgeInfoDTO addEdge(EdgeInfoDTO edgeInfoDto) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOByIpAndPort(edgeInfoDto.getIp(), edgeInfoDto.getPort());
        if (edgeInfoPO != null) {
            throw new RuntimeException("?????????????????????");
        }
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByName(edgeInfoDto.getName());
        if (edgeInfoPO != null) {
            throw new RuntimeException("??????????????????");
        }
        edgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, new Date());
        edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
        edgeInfoDao.save(edgeInfoPO);
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByIpAndPort(edgeInfoDto.getIp(), edgeInfoDto.getPort());
        return edgeInfoUtil.convertPO2DTO(edgeInfoPO);
    }

    @Override
    public EdgeInfoDTO updateEdgeInfoById(String edgeId, EdgeInfoDTO edgeInfoDto) {
        EdgeInfoPO existEdgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (existEdgeInfoPO != null) {
            EdgeInfoPO newEdgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, existEdgeInfoPO.getRegisterTimestamp());
            newEdgeInfoPO.setId(edgeId);
            newEdgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            edgeInfoDao.save(newEdgeInfoPO);
            return edgeInfoUtil.convertPO2DTO(newEdgeInfoPO);
        } else {
            throw new RuntimeException("?????????????????????");
        }
    }

    @Override
    public Boolean pingEdge(String edgeId) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(edgeInfoPO.getIp() + ':' + edgeInfoPO.getPort().toString() + edgeInfoPO.getApi());
        PingInfoRequest pingInfoRequest = edgeInfoUtil.createPingBody(edgeInfoPO);
        String jsonString = JSON.toJSONString(pingInfoRequest);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = null;
        try {
            // ??????????????????(??????)Post??????
            response = httpClient.execute(httpPost);
            // ????????????????????????????????????
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
//                System.out.println("?????????????????????:" + responseEntity.getContentLength());
                System.out.println("???????????????:" + EntityUtils.toString(responseEntity));
            }
            edgeInfoPO.setStatus(EdgeStatus.ONLINE);
            edgeInfoDao.save(edgeInfoPO);
            return true;
        } catch (IOException e) {
            edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boolean pingStopEdge(String edgeId) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (edgeInfoPO.getStatus() == EdgeStatus.OFFLINE) {
            throw new RuntimeException("??????????????????, ????????????");
        }
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(edgeInfoPO.getIp() + ':' + edgeInfoPO.getPort().toString() + edgeInfoPO.getApi() + "/stop");
        PingInfoRequest pingInfoRequest = edgeInfoUtil.createPingBody(edgeInfoPO);
        String jsonString = JSON.toJSONString(pingInfoRequest);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        CloseableHttpResponse response = null;
        try {
            // ??????????????????(??????)Post??????
            response = httpClient.execute(httpPost);
            // ????????????????????????????????????
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                System.out.println("?????????????????????:" + responseEntity.getContentLength());
                System.out.println("???????????????:" + EntityUtils.toString(responseEntity));
            }
            edgeInfoPO.setStatus(EdgeStatus.ONLINE);
            edgeInfoDao.save(edgeInfoPO);
            return true;
        } catch (IOException e) {
            edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            e.printStackTrace();
            return false;
        } finally {
            try {
                // ????????????
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
