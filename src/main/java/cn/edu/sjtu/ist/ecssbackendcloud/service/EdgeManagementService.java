package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;

import java.util.List;

public interface EdgeManagementService {

    List<EdgeInfoDto> getAllEdgeInfo();

    EdgeInfoDto getEdgeInfoById(String edgeId);

    EdgeInfoDto addEdge(EdgeInfoDto edgeInfoDto);

}
