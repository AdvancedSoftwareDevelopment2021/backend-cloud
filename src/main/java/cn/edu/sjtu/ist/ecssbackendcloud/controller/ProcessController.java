package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.ProcessRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Status;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Step;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.IssueProcessRequest;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ProcessDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ProcessService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.BpmnUtils;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.ProcessUtil;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @brief 流程controller
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Slf4j
@RestController
@RequestMapping(value = "/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;

    @Autowired
    private ProcessUtil processUtil;

    @PostMapping(value = "")
    public Result<?> insertProcessWithFile(@ModelAttribute ProcessRequest request) {
        String name = request.getName();
        String owner = request.getOwner();
        MultipartFile file = request.getFile();
        ProcessDTO dto = new ProcessDTO();
        dto.setOwner(owner);
        dto.setName(name);
        dto.setBpmn(BpmnUtils.multiFileToStr(file));
        dto.setCreatedTime(new Date());
        dto.setStep(Step.BPMN);
        dto.setStatus(Status.CONSTRUCTING);
        Process process = processUtil.convertDTO2Domain(dto);
        try {
            return ResultUtil.success(processService.insertProcess(process));
        } catch (RuntimeException exception) {
            return ResultUtil.failure("该名称已存在", HttpStatus.EXPECTATION_FAILED.value());
        }

    }

    @DeleteMapping(value = "/{id}")
    public Result<?> deleteProcess(@PathVariable String id) {
        processService.deleteProcess(id);
        return ResultUtil.success();
    }

    @PutMapping(value = "/{id}")
    public Result<?> updateProcess(@PathVariable String id, @RequestBody ProcessDTO dto) {
        Process process = processUtil.convertDTO2Domain(dto);
        processService.updateProcess(id, process);
        return ResultUtil.success();
    }

    @GetMapping(value = "/{id}")
    public Result<?> findProcess(@PathVariable("id") String id) {
        return ResultUtil.success(processService.findProcess(id));
    }

    @GetMapping(value = "/user/{userId}")
    public Result<?> getAllProcessesByUser(@PathVariable("userId") String userId) {
        return ResultUtil.success(processService.getAllProcessesByUser(userId));
    }

    @GetMapping(value = "")
    public Result<?> getAllProcesses() {
        return ResultUtil.success(processService.getAllProcesses());
    }

    @PutMapping("/{id}/step/{step}")
    public Result<?> updateProcessStep(@PathVariable String id, @PathVariable String step) {
        processService.updateProcessStep(id, Step.valueOf(step));
        return ResultUtil.success();
    }

    @PutMapping(value = "/{id}/name/{name}")
    public Result<?> updateProcessName(@PathVariable String id, @PathVariable String name) {
        processService.updateProcessName(id, name);
        return ResultUtil.success();
    }

    @GetMapping(value = "/owner/{owner}")
    public Result<?> findAllProcess(@PathVariable String owner) {
        return ResultUtil.success(processService.findOwnedProcesses(owner));
    }

    @GetMapping("/{id}/bpmn")
    public Result<String> findBpmn(@PathVariable String id) {
        return ResultUtil.success(processService.findBpmn(id));
    }

    @PutMapping("/{id}/bpmn")
    public Result<?> updateBpmn(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        processService.updateProcessBpmn(id, file);
        return ResultUtil.success();
    }

    @PostMapping("/issue")
    public Result<?> issueProcess(@ModelAttribute IssueProcessRequest issueProcessRequest) {
        Boolean result = processService.issueProcess(issueProcessRequest.getIp(), issueProcessRequest.getPort(),
                issueProcessRequest.getProcessDTO());
        if (result == true) {
            return ResultUtil.success(true);
        } else {
            return ResultUtil.failure("Fail to issue the process to edge end.", HttpStatus.EXPECTATION_FAILED.value());
        }
    }

}
