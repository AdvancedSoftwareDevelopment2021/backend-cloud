package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;

public interface EdgeManagementService {

    Response addEdge(EdgeInfoDTO edgeInfoDto);

    Response deleteEdgeInfoById(String edgeId);

    Response updateEdgeInfoById(String edgeId, EdgeInfoDTO edgeInfoDto);

    Response getAllEdgeInfo();

    Response getEdgeInfoById(String edgeId);

    Response pingEdge(String edgeId);

    Response pingStopEdge(String edgeId);
}
