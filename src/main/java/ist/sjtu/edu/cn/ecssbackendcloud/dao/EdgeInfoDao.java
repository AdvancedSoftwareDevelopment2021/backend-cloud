package ist.sjtu.edu.cn.ecssbackendcloud.dao;

import ist.sjtu.edu.cn.ecssbackendcloud.entity.dto.EdgeInfoDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeInfoDao extends MongoRepository<EdgeInfoDto, Long> {
}
