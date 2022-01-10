package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.IssueModelRequest;
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
    public Result<ModelInfoDTO> insertModelInfo(@RequestBody ModelInfoDTO request) {
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

    @PostMapping("/issue")
    public Result<Boolean> issueModel(@ModelAttribute IssueModelRequest issueModelRequest) {
        String id = issueModelRequest.getId();
        String ip = issueModelRequest.getIp();
        String port = issueModelRequest.getPort();
        return ResultUtil.success(modelManagementService.issueModel(id, ip, port));
    }

}
