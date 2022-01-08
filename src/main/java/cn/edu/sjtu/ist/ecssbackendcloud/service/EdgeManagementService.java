package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import java.util.*;

public interface EdgeManagementService {

    EdgeInfoDTO addEdge(EdgeInfoDTO edgeInfoDto);

    void deleteEdgeInfoById(String edgeId);

    EdgeInfoDTO updateEdgeInfoById(String edgeId, EdgeInfoDTO edgeInfoDto);

    List<EdgeInfoDTO> getAllEdgeInfoByUser(String userId);

    List<EdgeInfoDTO> getAllEdgeInfo();

    EdgeInfoDTO getEdgeInfoById(String edgeId);

    Boolean pingEdge(String edgeId);

    Boolean pingStopEdge(String edgeId);
}
