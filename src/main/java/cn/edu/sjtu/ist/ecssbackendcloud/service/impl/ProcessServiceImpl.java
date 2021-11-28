package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.ProcessDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ProcessDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.service.ProcessService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.ProcessUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private ProcessUtil processUtil;

    @Autowired
    private ProcessDao processDao;

    @Override
    public Response insertProcess(ProcessDTO processDTO) {
        Process process = processUtil.convertDTO2Domain(processDTO);
        processDao.createProcess(process);
        return new Response(200, "插入流程成功!", null);
    }

    @Override
    public Response deleteProcess(String id) {
        processDao.removeProcess(id);
        return new Response(200, "删除流程id=" + id + "成功!", null);
    }

    @Override
    public Response updateProcess(String id, ProcessDTO processDTO) {
        processDTO.setId(id);
        log.info(processDTO.toString());
        Process process = processUtil.convertDTO2Domain(processDTO);
        processDao.modifyProcess(process);
        return new Response(200, "更新流程id=" + id + "成功!", null);
    }

    @Override
    public Response getProcess(String id) {
        Process process = processDao.findProcessById(id);
        ProcessDTO processDTO = processUtil.convertDomain2DTO(process);
        return new Response(200, "获取流程id=" + id + "成功!", processDTO);
    }

    @Override
    public Response getAllProcesses() {
        List<Process> processs = processDao.findAllProcesss();
        List<ProcessDTO> res = new ArrayList<>();
        for (Process process: processs) {
            ProcessDTO dto = processUtil.convertDomain2DTO(process);
            res.add(dto);
        }
        return new Response(200, "获取所有流程成功", res);
    }
}
