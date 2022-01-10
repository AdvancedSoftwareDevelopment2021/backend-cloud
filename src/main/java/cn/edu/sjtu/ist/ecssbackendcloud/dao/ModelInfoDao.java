package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ModelInfoPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelInfoDao extends MongoRepository<ModelInfoPO, String> {

    ModelInfoPO findModelInfoPOById(String id);

    void deleteModelInfoPOById(String id);

    ModelInfoPO findModelInfoPOByName(String name);

    List<ModelInfoPO> findModelInfoPOSByOwner(String userId);
}
