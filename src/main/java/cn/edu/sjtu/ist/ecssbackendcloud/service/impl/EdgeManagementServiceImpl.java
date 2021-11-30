package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.EdgeInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
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

    @Override
    public Response getAllEdgeInfo() {
        List<EdgeInfoPO> edgeInfoPOList = edgeInfoDao.findAll();
        if (!edgeInfoPOList.isEmpty()) {
            return new Response(200, "获取边缘端信息列表成功", edgeInfoPOList);
        } else {
            return new Response(200, "获取边缘端信息列表失败", null);
        }
    }

    @Override
    public Response getEdgeInfoById(String EdgeId) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(EdgeId);
        if (edgeInfoPO != null) {
            return new Response(200, "获取 edge info id=" + EdgeId + "成功", edgeInfoPO);
        } else {
            return new Response(200, "获取 edge info id=" + EdgeId + "失败", null);
        }
    }

    @Override
    public Response deleteEdgeInfoById(String EdgeId) {
        if (edgeInfoDao.findEdgeInfoPOById(EdgeId) == null) {
            return new Response(200, "删除 edge info id=" + EdgeId + "失败，不存在该边缘端节点", null);
        }
        edgeInfoDao.deleteEdgeInfoPOById(EdgeId);
        return new Response(200, "删除 edge info id=" + EdgeId + "成功", null);
    }

    @Override
    public Response addEdge(EdgeInfoDTO edgeInfoDto) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOByIpAndPort(edgeInfoDto.getIp(), edgeInfoDto.getPort());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该边缘端已存在", null);
        }
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByName(edgeInfoDto.getName());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该名称已存在", null);
        }
        edgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, new Date());
        edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
        edgeInfoDao.save(edgeInfoPO);
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByIpAndPort(edgeInfoDto.getIp(), edgeInfoDto.getPort());
        return new Response(200, "创建成功, 边缘端id=" + edgeInfoPO.getId(), edgeInfoUtil.convertPO2DTO(edgeInfoPO));
    }

    @Override
    public Response updateEdgeInfoById(String edgeId, EdgeInfoDTO edgeInfoDto) {
        EdgeInfoPO existEdgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (existEdgeInfoPO != null) {
            EdgeInfoPO newEdgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, existEdgeInfoPO.getRegisterTimestamp());
            newEdgeInfoPO.setId(edgeId);
            newEdgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            edgeInfoDao.save(newEdgeInfoPO);
            return new Response(200, "边缘端id=" + edgeId + "信息修改成功", null);
        } else {
            return new Response(200, "修改边缘端id=" + edgeId + "信息失败，该边缘端不存在", null);
        }
    }

    @Override
    public Response pingEdge(String edgeId) {
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
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            edgeInfoPO.setStatus(EdgeStatus.ONLINE);
            edgeInfoDao.save(edgeInfoPO);
            return new Response(200, "边缘端id=" + edgeId + "连接成功", true);
        } catch (IOException e) {
            edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            e.printStackTrace();
            return new Response(200, "边缘端id=" + edgeId + "连接失败", false);
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
    public Response pingStopEdge(String edgeId) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (edgeInfoPO.getStatus() == EdgeStatus.OFFLINE) {
            return new Response(200, "边缘端id=" + edgeId + "未连接, 无法断连", true);
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
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            edgeInfoPO.setStatus(EdgeStatus.ONLINE);
            edgeInfoDao.save(edgeInfoPO);
            return new Response(200, "边缘端id=" + edgeId + "连接成功", true);
        } catch (IOException e) {
            edgeInfoPO.setStatus(EdgeStatus.OFFLINE);
            e.printStackTrace();
            return new Response(200, "边缘端id=" + edgeId + "连接失败", false);
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

}
