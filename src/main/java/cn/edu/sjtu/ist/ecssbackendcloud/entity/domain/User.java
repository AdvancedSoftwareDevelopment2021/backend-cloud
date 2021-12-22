package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
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
public class User {

    private String id;

    private String username;

    private String password;

    private UserPermission permission;

    /**
     * 用户创建的流程
     */
    private List<String> processes;

    public void addProcess(String processId) {
        processes.add(processId);
    }

    public void updatePasswd(String passwd) {
        this.password = passwd;
    }

    public String getPassword() {
        return this.password;
    }

    public String getToken() {
        String token = "";
        token = JWT.create()
                .withAudience(id)
                .sign(Algorithm.HMAC256(password));
        return token;
    }

    public boolean checkPasswd(String password) {
        return this.password.equals(password);
    }

    public boolean isAdmin() {
        return this.permission == UserPermission.ADMIN;
    }
}
