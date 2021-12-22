package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.List;

/**
 * @brief 用户PO类
 * @author rsp
 * @date 2021-12-21
 */
@Data
@Document("user")
public class UserPO {

    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String password;

    @Field
    private String permission;

    /**
     * 用户创建的流程
     */
    @Field
    private List<String> processes;
}
