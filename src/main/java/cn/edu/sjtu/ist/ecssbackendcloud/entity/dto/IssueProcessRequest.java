package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IssueProcessRequest {

    private ProcessDTO processDTO;

    private String ip;

    private String port;

}
