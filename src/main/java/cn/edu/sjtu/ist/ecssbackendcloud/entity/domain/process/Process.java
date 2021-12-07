package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process.proxy.BpmnModelProxy;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.BpmnUtils;

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

    /**
     * 与边端 id 对应
     */
    private String owner;

    private String name;

    private String bpmn;

    private Date createdTime;

    private Step step = Step.BPMN;

    private Status status = Status.CONSTRUCTING;

    public void verifySelf() {
        BpmnModelProxy bpmnModelProxy = BpmnModelProxy.fromStream(BpmnUtils.strToInStream(this.bpmn));
        bpmnModelProxy.verifySelf();
    }
}
