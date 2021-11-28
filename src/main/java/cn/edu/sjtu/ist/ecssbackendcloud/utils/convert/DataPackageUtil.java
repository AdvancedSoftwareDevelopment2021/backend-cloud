package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.DataPackage;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.DataPackageDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.DataPackagePO;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author dyanjun
 * @date 2021/11/28 15:19
 */
@Component
public class DataPackageUtil {
    public DataPackage convertDTO2Domain(DataPackageDTO dto) {
        DataPackage res = new DataPackage();
        res.setEdgeId(dto.getEdgeId());
        res.setTimestamp(dto.getTimestamp() == null ? new Date() : dto.getTimestamp());
        return res;
    }

    public DataPackageDTO convertDomain2DTO(DataPackage domain) {
        DataPackageDTO res = new DataPackageDTO();
        res.setId(domain.getId());
        res.setEdgeId(domain.getEdgeId());
        res.setTimestamp(domain.getTimestamp() == null ? new Date() : domain.getTimestamp());
        return res;
    }

    public DataPackage convertPO2Domain(DataPackagePO po) {
        DataPackage res = new DataPackage();
        res.setId(po.getId());
        res.setEdgeId(po.getEdgeId());
        res.setDataPackageFile(po.getDataPackageFile());
        res.setTimestamp(po.getTimestamp() == null ? new Date() : po.getTimestamp());
        return res;
    }

    public DataPackagePO convertDomain2PO(DataPackage domain) {
        DataPackagePO res = new DataPackagePO();
        res.setId(domain.getId());
        res.setEdgeId(domain.getEdgeId());
        res.setDataPackageFile(domain.getDataPackageFile());
        res.setTimestamp(domain.getTimestamp() == null ? new Date() : domain.getTimestamp());
        return res;
    }
}
