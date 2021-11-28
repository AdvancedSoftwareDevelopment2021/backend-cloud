package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.DataPackageDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.DataPackageDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.service.DataPackageService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.DataPackageUtil;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class DataPackageServiceImpl implements DataPackageService {

    @Autowired
    private DataPackageUtil dataPackageUtil;

    @Autowired
    private DataPackageDao dataPackageDao;

    @Override
    public Response receiveDataPackage(String edgeId, MultipartFile file) {
        DataPackage dataPackage = new DataPackage();
        dataPackage.setDataPackageFile(file);
        dataPackage.setEdgeId(edgeId);
        dataPackageDao.createDataPackage(dataPackage);
        return new Response(200, "OK", "接收成功");
    }

    @Override
    public Response getDataPackageByEdgeId(String edgeId) {
        List<DataPackage> dataPackageList = dataPackageDao.findDataPackagesByEdgeId(edgeId);
        List<DataPackageDTO> dataPackageDTOList = new ArrayList<>();
        for(DataPackage dataPackage: dataPackageList){
            DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
            // TODO dataPackageDTO 的 edge的名称
            dataPackageDTO.setFilename(dataPackage.getDataPackageFile().getName());
            dataPackageDTOList.add(dataPackageDTO);
        }
        return new Response(200, "OK", dataPackageDTOList);
    }

    @Override
    public Response getDataPackage() {
        List<DataPackage> dataPackageList = dataPackageDao.findAll();
        List<DataPackageDTO> dataPackageDTOList = new ArrayList<>();
        for(DataPackage dataPackage: dataPackageList){
            DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
            // TODO dataPackageDTO 的 edge的名称
            dataPackageDTO.setFilename(dataPackage.getDataPackageFile().getName());
            dataPackageDTOList.add(dataPackageDTO);
        }
        return new Response(200, "OK", dataPackageDTOList);
    }

    @Override
    public Response getDataPackageById(String id) {
        DataPackage dataPackage = dataPackageDao.findDataPackageById(id);
        DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
        // TODO dataPackageDTO 的 edge的名称
        dataPackageDTO.setFilename(dataPackage.getDataPackageFile().getName());
        return new Response(200, "OK", dataPackageDTO);
    }
}
