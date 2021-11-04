package ist.sjtu.edu.cn.ecssbackendcloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EdgeInfoDto {

    private String id;

    private String name;

    private String processId;

    private String owner;

    private String responsor;

    private String ip;

    private String port;

}
