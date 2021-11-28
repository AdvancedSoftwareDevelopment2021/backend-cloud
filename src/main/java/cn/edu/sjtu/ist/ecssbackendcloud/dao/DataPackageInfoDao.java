package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataPackageInfoDao extends MongoRepository<DataPackageInfoDao, String> {
}
