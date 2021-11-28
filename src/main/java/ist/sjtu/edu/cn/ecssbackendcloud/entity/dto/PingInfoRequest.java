package ist.sjtu.edu.cn.ecssbackendcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PingInfoRequest {

    private String id;

    private String timeUnit;

    private String interval;

    private String url;

}
