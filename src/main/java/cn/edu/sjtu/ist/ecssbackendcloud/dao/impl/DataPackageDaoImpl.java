package cn.edu.sjtu.ist.ecssbackendcloud.dao.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.DataPackageDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.DataPackagePO;
import cn.edu.sjtu.ist.ecssbackendcloud.repository.DataPackageRepository;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.DataPackageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dyanjun
 * @date 2021/11/28 15:47
 */
@Slf4j
@Component
public class DataPackageDaoImpl implements DataPackageDao {

    @Autowired
    private DataPackageUtil dataPackageUtil;

    @Autowired
    private DataPackageRepository dataPackageRepository;

    @Override
    public void createDataPackage(DataPackage dataPackage) {
        DataPackagePO dataPackagePO = dataPackageUtil.convertDomain2PO(dataPackage);
        dataPackageRepository.save(dataPackagePO);
    }

    @Override
    public void deleteDataPackageById(String id) {
        dataPackageRepository.deleteDataPackagePOById(id);
    }

    @Override
    public DataPackage findDataPackageById(String id) {
        DataPackagePO dataPackagePO = dataPackageRepository.findDataPackagePOById(id);
        if(dataPackagePO != null){
            DataPackage dataPackage = dataPackageUtil.convertPO2Domain(dataPackagePO);
            return dataPackage;
        }
        return null;
    }

    @Override
    public List<DataPackage> findDataPackagesByEdgeId(String edgeId) {
        List<DataPackagePO> dataPackagePOList = dataPackageRepository.findDataPackagePOSByEdgeId(edgeId);
        List<DataPackage> dataPackageList = new ArrayList<>();
        for(DataPackagePO dataPackagePO : dataPackagePOList){
            DataPackage dataPackage = dataPackageUtil.convertPO2Domain(dataPackagePO);
            dataPackageList.add(dataPackage);
        }
        return dataPackageList;
    }

    @Override
    public List<DataPackage> findAll() {
        List<DataPackagePO> dataPackagePOList = dataPackageRepository.findAll();
        List<DataPackage> dataPackageList = new ArrayList<>();
        for(DataPackagePO dataPackagePO : dataPackagePOList){
            DataPackage dataPackage = dataPackageUtil.convertPO2Domain(dataPackagePO);
            dataPackageList.add(dataPackage);
        }
        return dataPackageList;
    }
}
