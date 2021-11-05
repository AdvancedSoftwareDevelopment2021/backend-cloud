package ist.sjtu.edu.cn.ecssbackendcloud.service;

import ist.sjtu.edu.cn.ecssbackendcloud.dto.EdgeInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

public interface EdgeManagementService {

    List<EdgeInfoDto> getAllEdgeInfo();

    EdgeInfoDto getEdgeInfoById(String edgeId);

    EdgeInfoDto addEdge(EdgeInfoDto edgeInfoDto);

}
