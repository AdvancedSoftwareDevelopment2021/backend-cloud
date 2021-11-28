package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.ProcessDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.ProcessPO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Process;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @brief 流程类转换工具类
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Component
public class ProcessUtil {

    public Process convertDTO2Domain(ProcessDTO dto) {
        Process res = new Process();
        res.setName(dto.getName());
        res.setId(dto.getId());
        res.setBpmn(dto.getBpmn());
        res.setCreatedTime(dto.getCreatedTime() == null ? new Date() : dto.getCreatedTime());
        return res;
    }

    public ProcessDTO convertDomain2DTO(Process domain) {
        ProcessDTO res = new ProcessDTO();
        res.setName(domain.getName());
        res.setId(domain.getId());
        res.setBpmn(domain.getBpmn());
        res.setCreatedTime(domain.getCreatedTime() == null ? new Date() : domain.getCreatedTime());
        return res;
    }

    public Process convertPO2Domain(ProcessPO po) {
        Process res = new Process();
        res.setName(po.getName());
        res.setId(po.getId());
        res.setBpmn(po.getBpmn());
        res.setCreatedTime(po.getCreatedTime() == null ? new Date() : po.getCreatedTime());
        return res;
    }

    public ProcessPO convertDomain2PO(Process domain) {
        ProcessPO res = new ProcessPO();
        res.setName(domain.getName());
        res.setId(domain.getId());
        res.setBpmn(domain.getBpmn());
        res.setCreatedTime(domain.getCreatedTime() == null ? new Date() : domain.getCreatedTime());
        return res;
    }
}