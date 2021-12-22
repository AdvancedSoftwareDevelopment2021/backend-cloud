package cn.edu.sjtu.ist.ecssbackendcloud.utils.convert;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.UserPermission;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.UserDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.UserPO;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @brief 用户类转换工具类
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Component
public class UserUtil {

    public User convertDTO2Domain(UserDTO dto) {
        User res = new User();
        res.setId(dto.getId());
        res.setUsername(dto.getUsername());
        res.setPassword(dto.getPassword());
        res.setPermission(UserPermission.fromString(dto.getPermission()));
        res.setProcesses(dto.getProcesses() == null ? new ArrayList<>() : dto.getProcesses());
        return res;
    }

    public UserDTO convertDomain2DTO(User domain) {
        UserDTO res = new UserDTO();
        res.setId(domain.getId());
        res.setUsername(domain.getUsername());
        res.setPassword(domain.getPassword());
        res.setPermission(domain.getPermission().getPermission());
        res.setProcesses(domain.getProcesses());
        res.setProcesses(domain.getProcesses() == null ? new ArrayList<>() : domain.getProcesses());
        return res;
    }

    public User convertPO2Domain(UserPO po) {
        User res = new User();
        res.setId(po.getId());
        res.setUsername(po.getUsername());
        res.setPassword(po.getPassword());
        res.setPermission(UserPermission.fromString(po.getPermission()));
        res.setProcesses(po.getProcesses());
        res.setProcesses(po.getProcesses() == null ? new ArrayList<>() : po.getProcesses());
        return res;
    }

    public UserPO convertDomain2PO(User domain) {
        UserPO res = new UserPO();
        res.setId(domain.getId());
        res.setUsername(domain.getUsername());
        res.setPassword(domain.getPassword());
        res.setPermission(domain.getPermission().getPermission());
        res.setProcesses(domain.getProcesses() == null ? new ArrayList<>() : domain.getProcesses());
        return res;
    }
}
