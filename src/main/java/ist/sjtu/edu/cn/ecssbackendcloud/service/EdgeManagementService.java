package ist.sjtu.edu.cn.ecssbackendcloud.service;

import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.EdgeInfoDto;
import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.Response;

import java.util.List;

public interface EdgeManagementService {

    Response addEdge(EdgeInfoDto edgeInfoDto);

    Response deleteEdgeInfoById(String edgeId);

    Response updateEdgeInfoById(String edgeId, EdgeInfoDto edgeInfoDto);

    Response getAllEdgeInfo();

    Response getEdgeInfoById(String edgeId);

    Response pingEdge(String edgeId);

}
