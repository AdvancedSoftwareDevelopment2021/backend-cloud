package cn.edu.sjtu.ist.ecssbackendcloud.service.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.DataPackageDao;
import cn.edu.sjtu.ist.ecssbackendcloud.dao.EdgeInfoDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.DataPackageDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.Response;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.EdgeInfoPO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.DataPackageService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.DataPackageUtil;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DataPackageServiceImpl implements DataPackageService {

    @Autowired
    private DataPackageUtil dataPackageUtil;

    @Autowired
    private DataPackageDao dataPackageDao;

    @Autowired
    private EdgeInfoDao edgeInfoDao;

    @Override
    public void receiveDataPackage(DataPackage dataPackage) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(dataPackage.getEdgeId());
        if(edgeInfoPO == null) {
            throw new RuntimeException("边缘端不存在");
        }
        dataPackage.setEdgeName(edgeInfoPO.getName());
        dataPackageDao.createDataPackage(dataPackage);
    }

    @Override
    public List<DataPackageDTO> getDataPackageByEdgeId(String edgeId) {
        List<DataPackage> dataPackageList = dataPackageDao.findDataPackagesByEdgeId(edgeId);
        List<DataPackageDTO> dataPackageDTOList = new ArrayList<>();
        for(DataPackage dataPackage: dataPackageList){
            DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
            dataPackageDTOList.add(dataPackageDTO);
        }
        return dataPackageDTOList;
    }

    @Override
    public List<DataPackageDTO> getDataPackage() {
        List<DataPackage> dataPackageList = dataPackageDao.findAll();
        List<DataPackageDTO> dataPackageDTOList = new ArrayList<>();
        for(DataPackage dataPackage: dataPackageList){
            DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
            dataPackageDTOList.add(dataPackageDTO);
        }
        return dataPackageDTOList;
    }

    @Override
    public DataPackageDTO getDataPackageById(String id) {
        DataPackage dataPackage = dataPackageDao.findDataPackageById(id);
        DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
        return dataPackageDTO;
    }

    @Override
    public void deleteDataPackage(String id) {
        dataPackageDao.deleteDataPackageById(id);
    }
}
