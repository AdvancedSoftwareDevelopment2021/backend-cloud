package cn.edu.sjtu.ist.ecssbackendcloud.service;


import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;

public interface DataPackageService {

    Response receiveDataPackage(DataPackage dataPackage);

    Response getDataPackageByEdgeId(String edgeId);

    Response getDataPackage();

    Response getDataPackageById(String id);

    Response deleteDataPackage(String id);
}
