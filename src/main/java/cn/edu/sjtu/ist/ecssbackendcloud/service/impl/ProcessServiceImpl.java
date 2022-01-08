package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.ProcessDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Step;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ProcessService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.BpmnUtils;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.DataStoreReference;
import org.camunda.bpm.model.bpmn.instance.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

/**
 * @brief 流程 serviceImpl
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Slf4j
@Service
public class ProcessServiceImpl implements ProcessService {

    @Autowired
    private ProcessDao processDao;

    @Override
    public Process insertProcess(Process process) {
        processDao.createProcess(process);
        log.info("插入流程成功!");
        return processDao.findProcessByName(process.getName()).get(0);
    }

    @Override
    public Process insertProcessWithFile(Process process, MultipartFile file) {
        String bpmn = BpmnUtils.multiFileToStr(file);
        verifyName(bpmn);
        process.setBpmn(bpmn);
        return insertProcess(process);
    }

    @Override
    public void deleteProcess(String id) {
        processDao.removeProcess(id);
        log.info("删除流程id={}成功!", id);
    }

    @Override
    public void updateProcess(String id, Process process) {
        process.setId(id);
        log.info(process.toString());
        processDao.modifyProcess(process);
        log.info("更新流程id={}成功!", id);
    }

    @Override
    public List<Process> getAllProcessesByUser(String userId) {
        List<Process> processes = processDao.findProcessesByOwner(userId);
        log.info("获取用户id=" + userId + "的所有流程成功");
        return processes;
    }

    @Override
    public List<Process> getAllProcesses() {
        List<Process> processes = processDao.findAllProcesses();
        log.info("获取所有流程成功");
        return processes;
    }

    private void verifyName(String bpmn){
        BpmnModelInstance instance = Bpmn.readModelFromStream(BpmnUtils.strToInStream(bpmn));
        Collection<? extends Task> tasks = instance.getModelElementsByType(Task.class);
        for (Task task : tasks) {
            Assert.notNull(task.getName(), "Task节点应该绑定名称");
        }
        Collection<? extends DataStoreReference> dsrs = instance.getModelElementsByType(DataStoreReference.class);
        for (DataStoreReference dsr : dsrs) {
            Assert.notNull(dsr.getName(), "数据节点应该绑定名称");
        }
    }

    @Override
    public void updateProcessBpmn(String processId, MultipartFile file) {
        String bpmn = BpmnUtils.multiFileToStr(file);
        verifyName(bpmn);
        Process process = findProcess(processId);
        process.setBpmn(bpmn);
        processDao.modifyProcess(process);
    }

    @Override
    public void updateProcessStep(String processId, Step step) {
        Process process = findProcess(processId);
        if (step == Step.FINISHED) {
            process.verifySelf();
        }
        process.setStep(step);
        processDao.modifyProcess(process);
    }

    @Override
    public void updateProcessName(String processId, String name) {
        Process process = findProcess(processId);
        process.setName(name);
        processDao.modifyProcess(process);
    }

    @Override
    public Process findProcess(String processId) {
        return processDao.findProcessById(processId);
    }

    @Override
    public List<Process> findOwnedProcesses(String owner) {
        return processDao.findProcessesByOwner(owner);
    }

    @Override
    public String findBpmn(String processId) {
        Process process = findProcess(processId);
        return process.getBpmn();
    }
}
