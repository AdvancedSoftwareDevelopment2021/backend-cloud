package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;

import java.util.List;

public interface EdgeManagementService {

    Response addEdge(EdgeInfoDto edgeInfoDto);

    Response deleteEdgeInfoById(String edgeId);

    Response updateEdgeInfoById(String edgeId, EdgeInfoDto edgeInfoDto);

    Response getAllEdgeInfo();

    Response getEdgeInfoById(String edgeId);

    Response pingEdge(String edgeId);

}
