package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.DataPackageInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.dao.EdgeInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.PingInfoRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.EdgeInfoUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
@Slf4j
public class EdgeManagementServiceImpl implements EdgeManagementService {

    @Autowired
    private EdgeInfoUtil edgeInfoUtil;

    @Autowired
    private EdgeInfoDao edgeInfoDao;

    @Autowired
    private DataPackageInfoDao dataPackageInfoDao;

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
    public Response addEdge(EdgeInfoDto edgeInfoDto) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOByUrl(edgeInfoDto.getUrl());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该边缘端已存在", null);
        }
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByName(edgeInfoDto.getName());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该名称已存在", null);
        }
        Date timestamp = new Date();
        edgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, timestamp);
        edgeInfoPO.setStatus("未连接");
        edgeInfoDao.save(edgeInfoPO);
        String edgeId = edgeInfoDao.findEdgeInfoPOByUrl(edgeInfoDto.getUrl()).getId();
        return new Response(200, "创建成功, 边缘端id=" + edgeId, null);
    }

    @Override
    public Response updateEdgeInfoById(String edgeId, EdgeInfoDto edgeInfoDto) {
        EdgeInfoPO existEdgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (existEdgeInfoPO != null) {
            EdgeInfoPO newEdgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, existEdgeInfoPO.getRegisterTimestamp());
            newEdgeInfoPO.setId(edgeId);
            newEdgeInfoPO.setStatus("未连接");
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

        String edgeAPI = "edge/";
        HttpPost httpPost = new HttpPost(edgeInfoPO.getUrl() + edgeAPI);
        PingInfoRequest pingInfoRequest = edgeInfoUtil.createPingBody(edgeInfoPO);
        String jsonString = JSON.toJSONString(pingInfoRequest);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        System.out.println(httpPost);

        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
            edgeInfoPO.setStatus("已连接");
            edgeInfoDao.save(edgeInfoPO);
            return new Response(200, "边缘端id=" + edgeId + "连接成功", null);
        } catch (IOException e) {
            e.printStackTrace();
            return new Response(200, "边缘端id=" + edgeId + "连接失败", null);
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
