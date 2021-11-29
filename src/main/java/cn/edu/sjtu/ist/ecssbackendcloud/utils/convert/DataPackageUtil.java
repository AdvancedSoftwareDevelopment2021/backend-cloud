package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.DataPackage;
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
    public DataPackageDTO convertDomain2DTO(DataPackage domain) {
        DataPackageDTO res = new DataPackageDTO();
        res.setId(domain.getId());
        res.setName(domain.getName());
        res.setEdgeId(domain.getEdgeId());
        res.setEdgeName(domain.getEdgeName());
        res.setTimestamp(domain.getTimestamp() == null ? new Date() : domain.getTimestamp());
        return res;
    }

    public DataPackage convertPO2Domain(DataPackagePO po) {
        DataPackage res = new DataPackage();
        res.setId(po.getId());
        res.setName(po.getName());
        res.setEdgeId(po.getEdgeId());
        res.setEdgeName(po.getEdgeName());
        res.setContentType(po.getContentType());
        res.setSize(po.getSize());
        res.setMd5(po.getMd5());
        res.setContent(po.getContent());
        res.setTimestamp(po.getTimestamp() == null ? new Date() : po.getTimestamp());
        return res;
    }

    public DataPackagePO convertDomain2PO(DataPackage domain) {
        DataPackagePO res = new DataPackagePO();
        res.setId(domain.getId());
        res.setName(domain.getName());
        res.setEdgeId(domain.getEdgeId());
        res.setEdgeName(domain.getEdgeName());
        res.setContentType(domain.getContentType());
        res.setSize(domain.getSize());
        res.setMd5(domain.getMd5());
        res.setContent(domain.getContent());
        res.setTimestamp(domain.getTimestamp() == null ? new Date() : domain.getTimestamp());
        return res;
    }
}
