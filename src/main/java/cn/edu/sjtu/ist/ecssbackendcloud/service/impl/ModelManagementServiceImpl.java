package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.EdgeInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.dao.ModelInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.IssueModelRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelEdgeDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ModelInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ModelManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.ModelInfoUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
@Slf4j
public class ModelManagementServiceImpl implements ModelManagementService {

    @Autowired
    private ModelInfoUtil modelInfoUtil;

    @Autowired
    private ModelInfoDao modelInfoDao;

    @Autowired
    private EdgeInfoDao edgeInfoDao;

    @Override
    public List<ModelInfoDTO> getAllModelInfoByUser(String userId) {
        List<ModelInfoPO> modelInfoPOList = modelInfoDao.findModelInfoPOSByOwner(userId);
        List<ModelInfoDTO> modelInfoDTOList = new ArrayList<>();
        for(ModelInfoPO modelInfoPO: modelInfoPOList) {
            modelInfoDTOList.add(modelInfoUtil.convertPO2DTO(modelInfoPO));
        }
        return modelInfoDTOList;
    }

    @Override
    public List<ModelInfoDTO> getAllModelInfo() {
        List<ModelInfoPO> modelInfoPOList = modelInfoDao.findAll();
        List<ModelInfoDTO> modelInfoDTOList = new ArrayList<>();
        for(ModelInfoPO modelInfoPO: modelInfoPOList) {
            modelInfoDTOList.add(modelInfoUtil.convertPO2DTO(modelInfoPO));
        }
        return modelInfoDTOList;
    }

    @Override
    public ModelInfoDTO getModelInfoById(String ModelId) {
        ModelInfoPO modelInfoPO = modelInfoDao.findModelInfoPOById(ModelId);
        if (modelInfoPO != null) {
            return modelInfoUtil.convertPO2DTO(modelInfoPO);
        } else {
            throw new RuntimeException("获取模型信息失败");
        }
    }

    @Override
    public void deleteModelInfoById(String ModelId) {
        if (modelInfoDao.findModelInfoPOById(ModelId) == null) {
            throw new RuntimeException("不存在该模型");
        }
        modelInfoDao.deleteModelInfoPOById(ModelId);
    }

    @Override
    public ModelInfoDTO addModel(ModelInfoDTO modelInfoDto) {
        ModelInfoPO modelInfoPO = modelInfoDao.findModelInfoPOByName(modelInfoDto.getName());
        if (modelInfoPO != null) {
            throw new RuntimeException("该名称已存在");
        }
        modelInfoPO = modelInfoUtil.convertDtoToPo(modelInfoDto, new Date());
        modelInfoDao.save(modelInfoPO);
        modelInfoUtil.saveModel(modelInfoDto.getFile(), modelInfoDto.getName());
        return modelInfoUtil.convertPO2DTO(modelInfoPO);
    }

    @Override
    public ModelInfoDTO updateModelInfoById(String modelId, ModelInfoDTO modelInfoDto) {
        ModelInfoPO existModelInfoPO = modelInfoDao.findModelInfoPOById(modelId);
        if (existModelInfoPO != null) {
            ModelInfoPO newModelInfoPO = modelInfoUtil.convertDtoToPo(modelInfoDto, existModelInfoPO.getRegisterTimestamp());
            newModelInfoPO.setId(modelId);
            modelInfoDao.save(newModelInfoPO);
            return modelInfoUtil.convertPO2DTO(newModelInfoPO);
        } else {
            throw new RuntimeException("该模型不存在");
        }
    }

    @Override
    public Boolean issueModel(String modelId, String ip, String port) {
        ModelInfoPO modelInfoPO = modelInfoDao.findModelInfoPOById(modelId);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = new HttpPost(ip + ':' + port + "/model");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addPart("file", new FileBody(modelInfoUtil.getModel(modelInfoPO.getName())));
        httpPost.setEntity(builder.build());

        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // 释放资源
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
    public List<ModelEdgeDTO> getModelEdgeList(String modelId) {
        ModelInfoPO modelInfoPO = modelInfoDao.findModelInfoPOById(modelId);
        List<String> edgeIdList = modelInfoPO.getEdgeIdList();
        List<ModelEdgeDTO> modelEdgeDTOList = new ArrayList<>();
        for (String edgeId: edgeIdList) {
            EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
            ModelEdgeDTO modelEdgeDTO = new ModelEdgeDTO();
            modelEdgeDTO.setName(edgeInfoPO.getName());
            modelEdgeDTO.setId(edgeInfoPO.getId());
            modelEdgeDTOList.add(modelEdgeDTO);
        }
        return modelEdgeDTOList;
    }
}
