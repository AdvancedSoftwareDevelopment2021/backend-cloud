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
        res.setId(dto.getId());
        res.setOwner(dto.getOwner());
        res.setName(dto.getName());
        res.setBpmn(dto.getBpmn());
        res.setStep(dto.getStep());
        res.setStatus(dto.getStatus());
        res.setCreatedTime(dto.getCreatedTime() == null ? new Date() : dto.getCreatedTime());
        return res;
    }

    public ProcessDTO convertDomain2DTO(Process domain) {
        ProcessDTO res = new ProcessDTO();
        res.setId(domain.getId());
        res.setOwner(domain.getOwner());
        res.setName(domain.getName());
        res.setBpmn(domain.getBpmn());
        res.setStep(domain.getStep());
        res.setStatus(domain.getStatus());
        res.setCreatedTime(domain.getCreatedTime() == null ? new Date() : domain.getCreatedTime());
        return res;
    }

    public Process convertPO2Domain(ProcessPO po) {
        Process res = new Process();
        res.setId(po.getId());
        res.setOwner(po.getOwner());
        res.setName(po.getName());
        res.setBpmn(po.getBpmn());
        res.setStep(po.getStep());
        res.setStatus(po.getStatus());
        res.setCreatedTime(po.getCreatedTime() == null ? new Date() : po.getCreatedTime());
        return res;
    }

    public ProcessPO convertDomain2PO(Process domain) {
        ProcessPO res = new ProcessPO();
        res.setId(domain.getId());
        res.setOwner(domain.getOwner());
        res.setName(domain.getName());
        res.setBpmn(domain.getBpmn());
        res.setStep(domain.getStep());
        res.setStatus(domain.getStatus());
        res.setCreatedTime(domain.getCreatedTime() == null ? new Date() : domain.getCreatedTime());
        return res;
    }
}