package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/edge")
public class EdgeManagementController {

    @Autowired
    private EdgeManagementService edgeManagementService;

    @PostMapping()
    public Result<EdgeInfoDTO> insertEdgeInfo(@RequestBody EdgeInfoDTO request) {
        return ResultUtil.success(edgeManagementService.addEdge(request));
    }

    @DeleteMapping("/{id}")
    public Result deleteEdgeInfo(@PathVariable String id) {
        edgeManagementService.deleteEdgeInfoById(id);
        return ResultUtil.success();
    }

    @PutMapping("/{id}")
    public Result<EdgeInfoDTO> updateEdgeInfo(@PathVariable String id, @RequestBody EdgeInfoDTO edgeInfoDto) {
        return ResultUtil.success(edgeManagementService.updateEdgeInfoById(id, edgeInfoDto));
    }

    @GetMapping("/list")
    public Result<List<EdgeInfoDTO>> getEdgeList() {
        return ResultUtil.success(edgeManagementService.getAllEdgeInfo());
    }

    @GetMapping("/{id}")
    public Result<EdgeInfoDTO> getEdgeInfo(@PathVariable String id) {
        return ResultUtil.success(edgeManagementService.getEdgeInfoById(id));
    }

    @GetMapping("/ping/{id}")
    public Result<Boolean> pingEdge(@PathVariable String id) {
        return ResultUtil.success(edgeManagementService.pingEdge(id));
    }

    @GetMapping("/ping/stop/{id}")
    public Result<Boolean> pingStopEdge(@PathVariable String id) {
        return ResultUtil.success(edgeManagementService.pingStopEdge(id));
    }

}
