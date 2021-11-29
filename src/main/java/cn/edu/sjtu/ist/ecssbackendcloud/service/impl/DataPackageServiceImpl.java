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
    public Response receiveDataPackage(DataPackage dataPackage) {
        EdgeInfoPO edgeInfoPO = edgeInfoDao.findEdgeInfoPOById(dataPackage.getEdgeId());
        if(edgeInfoPO == null) {
            return new Response(200, "边缘端不存在", "接收成功");
        }
        dataPackage.setEdgeName(edgeInfoPO.getName());
        dataPackageDao.createDataPackage(dataPackage);
        return new Response(200, "OK", "接收成功");
    }

    @Override
    public Response getDataPackageByEdgeId(String edgeId) {
        List<DataPackage> dataPackageList = dataPackageDao.findDataPackagesByEdgeId(edgeId);
        List<DataPackageDTO> dataPackageDTOList = new ArrayList<>();
        for(DataPackage dataPackage: dataPackageList){
            DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
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
            dataPackageDTOList.add(dataPackageDTO);
        }
        return new Response(200, "OK", dataPackageDTOList);
    }

    @Override
    public Response getDataPackageById(String id) {
        DataPackage dataPackage = dataPackageDao.findDataPackageById(id);
        DataPackageDTO dataPackageDTO = dataPackageUtil.convertDomain2DTO(dataPackage);
        return new Response(200, "OK", dataPackageDTO);
    }

    @Override
    public Response deleteDataPackage(String id) {
        dataPackageDao.deleteDataPackageById(id);
        return new Response(200, "删除成功", null);
    }
}
