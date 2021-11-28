package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.DataPackage;

import java.util.List;

public interface DataPackageDao{

    void createDataPackage(DataPackage dataPackage);

    void deleteDataPackageById(String id);

    DataPackage findDataPackageById(String id);

    List<DataPackage> findDataPackagesByEdgeId(String edgeId);

    List<DataPackage> findAll();
}
