package cn.edu.sjtu.ist.ecssbackendcloud.controller;


import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/edge")
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
