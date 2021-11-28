package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.EdgeInfoDto;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EdgeInfoDao extends MongoRepository<EdgeInfoPO, String> {

    EdgeInfoPO findEdgeInfoPOById(String id);

    void deleteEdgeInfoPOById(String id);

    EdgeInfoPO findEdgeInfoPOByUrl(String url);

    EdgeInfoPO findEdgeInfoPOByName(String name);

}
