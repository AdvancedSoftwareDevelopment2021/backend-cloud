package cn.edu.sjtu.ist.ecssbackendcloud.repository;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ProcessPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * @brief 流程 repository
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
public interface ProcessRepository extends MongoRepository<ProcessPO, String> {

    void deleteProcessPOById(String id);

    ProcessPO findProcessById(String id);

    List<ProcessPO> findProcessPOSByName(String name);

    List<ProcessPO> findAll();

}