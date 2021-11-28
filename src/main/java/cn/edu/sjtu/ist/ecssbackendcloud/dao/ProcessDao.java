package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;

import java.util.List;

/**
 * @brief 流程 dao
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
public interface ProcessDao {

    void createProcess(Process process);

    void removeProcess(String id);

    void modifyProcess(Process process);

    Process findProcessById(String id);

    List<Process> findProcessByName(String name);

    List<Process> findAllProcesss();
}
