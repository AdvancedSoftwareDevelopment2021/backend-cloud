package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.IssueModelRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelEdgeDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ModelInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.EdgeManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ModelManagementService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/model")
public class ModelManagementController {

    @Autowired
    private ModelManagementService modelManagementService;

    @PostMapping()
    public Result<ModelInfoDTO> insertModelInfo(@ModelAttribute ModelInfoDTO request) {
        return ResultUtil.success(modelManagementService.addModel(request));
    }

    @DeleteMapping("/{id}")
    public Result deleteModelInfo(@PathVariable String id) {
        modelManagementService.deleteModelInfoById(id);
        return ResultUtil.success();
    }

    @PutMapping("/{id}")
    public Result<ModelInfoDTO> updateModelInfo(@PathVariable String id, @RequestBody ModelInfoDTO modelInfoDto) {
        return ResultUtil.success(modelManagementService.updateModelInfoById(id, modelInfoDto));
    }

    @GetMapping("/list/user/{userId}")
    public Result<List<ModelInfoDTO>> getModelList(@PathVariable String userId) {
        return ResultUtil.success(modelManagementService.getAllModelInfoByUser(userId));
    }

    @GetMapping("/list")
    public Result<List<ModelInfoDTO>> getModelList() {
        return ResultUtil.success(modelManagementService.getAllModelInfo());
    }

    @GetMapping("/{id}")
    public Result<ModelInfoDTO> getModelInfo(@PathVariable String id) {
        return ResultUtil.success(modelManagementService.getModelInfoById(id));
    }

    @GetMapping("/bind")
    public Result<?> bindModel(@PathVariable String id, @PathVariable String edgeId) {
        Boolean result = modelManagementService.bindModelEdge(id, edgeId);
        if (result == true) {
            return ResultUtil.success(true);
        } else {
            return ResultUtil.failure("This model has been bound to the edge", -1);
        }

    }

    @GetMapping("/{id}/edge")
    public Result<List<ModelEdgeDTO>> getModelEdgeList(@PathVariable String id) {
        return ResultUtil.success(modelManagementService.getModelEdgeList(id));
    }

    @DeleteMapping("/{id}/edge/{edgeId}")
    public Result deleteModelEdge(@PathVariable String id, @PathVariable String edgeId) {
        modelManagementService.deleteModelEdge(id, edgeId);
        return ResultUtil.success();
    }

}
