package ist.sjtu.edu.cn.ecssbackendcloud.service;

import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.EdgeInfoDto;

import java.util.List;

public interface EdgeManagementService {

    List<EdgeInfoDto> getAllEdgeInfo();

    EdgeInfoDto getEdgeInfoById(String edgeId);

    EdgeInfoDto addEdge(EdgeInfoDto edgeInfoDto);

}
