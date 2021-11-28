package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import java.util.Date;

/**
 * @brief 流程 po
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Data
@Document("process")
public class ProcessPO {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String bpmn;

    @Field
    private Date createdTime;

}