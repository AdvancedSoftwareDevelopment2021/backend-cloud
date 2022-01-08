package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Step;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @brief 流程 service
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
public interface ProcessService {

    Process insertProcess(Process process);

    Process insertProcessWithFile(Process process, MultipartFile file);

    void deleteProcess(String id);

    void updateProcess(String id, Process process);

    List<Process> getAllProcessesByUser(String userId);

    List<Process> getAllProcesses();

    void updateProcessBpmn(String processId, MultipartFile file);

    void updateProcessStep(String processId, Step step);

    void updateProcessName(String processId, String name);

    Process findProcess(String processId);

    List<Process> findOwnedProcesses(String owner);

    String findBpmn(String processId);

}