package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Status;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.Step;

import lombok.Data;

import java.util.Date;

/**
 * @brief 流程 dto
 * @author rsp
 * @version 0.1
 * @date 2021-11-26
 */
@Data
public class ProcessDTO {

    private String id;

    private String owner;

    private String name;

    private String bpmn;

    private Date createdTime;

    private Step step;

    private Status status;
}
