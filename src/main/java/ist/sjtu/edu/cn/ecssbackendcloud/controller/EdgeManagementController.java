package ist.sjtu.edu.cn.ecssbackendcloud.controller;


import ist.sjtu.edu.cn.ecssbackendcloud.dto.EdgeInfoDto;
import ist.sjtu.edu.cn.ecssbackendcloud.service.EdgeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class EdgeManagementController {

    @Autowired
    private EdgeManagementService edgeManagementService;

    @GetMapping("/edge/list")
    public List<EdgeInfoDto> getEdgeList() {
        return edgeManagementService.getAllEdgeInfo();
    }

    @GetMapping("/edge")
    public EdgeInfoDto getEdgeInfo(@RequestParam("edgeId") String edgeId) {
        return edgeManagementService.getEdgeInfoById(edgeId);
    }

    @PostMapping("/edge/register")
    public EdgeInfoDto registerEdge(EdgeInfoDto edgeInfoDto) {
        return edgeManagementService.addEdge(edgeInfoDto);
    }

}
