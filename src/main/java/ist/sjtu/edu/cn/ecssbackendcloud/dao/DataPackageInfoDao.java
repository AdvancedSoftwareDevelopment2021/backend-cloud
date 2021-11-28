package ist.sjtu.edu.cn.ecssbackendcloud.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPackageInfoDao extends MongoRepository<DataPackageInfoDao, String> {
}
