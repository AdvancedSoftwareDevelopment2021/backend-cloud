package cn.edu.sjtu.ist.ecssbackendcloud.service;


import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import org.springframework.web.multipart.MultipartFile;

public interface DataPackageService {

    Response receiveDataPackage(String edgeId, MultipartFile file);

    Response getDataPackageByEdgeId(String edgeId);

    Response getDataPackage();

    Response getDataPackageById(String id);
}
