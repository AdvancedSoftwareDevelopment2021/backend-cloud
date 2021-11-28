package cn.edu.sjtu.ist.ecssbackendcloud.repository;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.DataPackagePO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author dyanjun
 * @date 2021/11/28 17:14
 */
@Repository
public interface DataPackageRepository extends MongoRepository<DataPackagePO, String> {
    void deleteDataPackagePOById(String id);

    DataPackagePO findDataPackagePOById(String id);

    List<DataPackagePO> findDataPackagePOSByEdgeId(String edgeId);

    List<DataPackagePO> findAll();
}