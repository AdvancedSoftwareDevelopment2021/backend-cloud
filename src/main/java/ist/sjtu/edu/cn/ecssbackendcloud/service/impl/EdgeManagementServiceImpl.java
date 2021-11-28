package ist.sjtu.edu.cn.ecssbackendcloud.service.impl;

import ist.sjtu.edu.cn.ecssbackendcloud.dao.DataPackageInfoDao;
import ist.sjtu.edu.cn.ecssbackendcloud.dao.EdgeInfoDao;
import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.EdgeInfoDto;
import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.Response;
import ist.sjtu.edu.cn.ecssbackendcloud.entity.po.EdgeInfoPO;
import ist.sjtu.edu.cn.ecssbackendcloud.service.EdgeManagementService;
import ist.sjtu.edu.cn.ecssbackendcloud.utils.EdgeInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        edgeInfoDao.deleteEdgeInfoPOById(EdgeId);
        return new Response(200, "删除 edge info id=" + EdgeId + "成功", null);
    }

    @Override
    public Response addEdge(EdgeInfoDto edgeInfoDto) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOByAddressAndPort(edgeInfoDto.getAddress(), edgeInfoDto.getPort());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该边缘端已存在", null);
        }
        edgeInfoPO = edgeInfoDao.findEdgeInfoPOByName(edgeInfoDto.getName());
        if (edgeInfoPO != null) {
            return new Response(300, "创建失败, 该名称已存在", null);
        }
        Date timestamp = new Date();
        edgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, timestamp);
        edgeInfoDao.save(edgeInfoPO);
        String edgeId = edgeInfoDao.findEdgeInfoPOByAddressAndPort(edgeInfoDto.getAddress(), edgeInfoDto.getPort()).getId();
        return new Response(200, "创建成功, 边缘端id=" + edgeId, null);
    }

    @Override
    public Response updateEdgeInfoById(String edgeId, EdgeInfoDto edgeInfoDto) {
        EdgeInfoPO existEdgeInfoPO = edgeInfoDao.findEdgeInfoPOById(edgeId);
        if (existEdgeInfoPO != null) {
            EdgeInfoPO newEdgeInfoPO = edgeInfoUtil.convertDtoToPo(edgeInfoDto, existEdgeInfoPO.getRegisterTimestamp());
            newEdgeInfoPO.setId(edgeId);
            edgeInfoDao.save(newEdgeInfoPO);
            return new Response(200, "边缘端id=" + edgeId + "信息修改成功", null);
        } else {
            return new Response(200, "修改边缘端id=" + edgeId + "信息失败，该边缘端不存在", null);
        }
    }
}
