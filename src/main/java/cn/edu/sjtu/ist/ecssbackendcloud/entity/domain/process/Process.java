package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process;

import lombok.Data;

import java.util.Date;

/**
 * @brief 流程领域模型
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Data
public class Process {

    private String id;

    private String name;

    private String bpmn;

    private Date createdTime;

}
