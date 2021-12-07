package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Step;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ProcessDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ProcessService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.ProcessUtil;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public Result<?> insertProcess(@RequestBody ProcessDTO dto) {
        Process process = processUtil.convertDTO2Domain(dto);
        return ResultUtil.success(processService.insertProcess(process));
    }

    @PostMapping(value = "/insert")
    public Result<?> insertProcessWithFile(@RequestBody ProcessDTO dto,
                                           @RequestParam("file") MultipartFile file) {
        Process process = processUtil.convertDTO2Domain(dto);
        return ResultUtil.success(processService.insertProcessWithFile(process, file));
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
}
