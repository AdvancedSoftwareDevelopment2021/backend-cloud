package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @brief 用户类
 * @author rsp
 * @date 2021-12-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String id;

    private String username;

    private String password;

    private String permission;

    /**
     * 用户创建的流程
     */
    private List<String> processes;
}
