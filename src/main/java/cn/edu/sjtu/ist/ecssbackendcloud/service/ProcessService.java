package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ProcessDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;

/**
 * @brief 流程 service
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
public interface ProcessService {

    Response insertProcess(ProcessDTO processDTO);

    Response deleteProcess(String id);

    Response updateProcess(String id, ProcessDTO processDTO);

    Response getProcess(String id);

    Response getAllProcesses();

}