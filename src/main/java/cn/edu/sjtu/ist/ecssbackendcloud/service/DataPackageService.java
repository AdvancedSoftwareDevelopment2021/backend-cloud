package cn.edu.sjtu.ist.ecssbackendcloud.service;


import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.DataPackageDTO;
import java.util.*;

public interface DataPackageService {

    void receiveDataPackage(DataPackage dataPackage);

    List<DataPackageDTO> getDataPackageByEdgeId(String edgeId);

    List<DataPackageDTO> getDataPackage();

    DataPackageDTO getDataPackageById(String id);

    void deleteDataPackage(String id);
}
