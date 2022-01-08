package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EdgeInfoDTO {

    private String id;

    private String name;

    private String owner;

    private String description;

    private String ip;

    private Integer port;

    private String api;

    private TimeUnit timeUnit;

    private Integer interval;

    private EdgeStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date registerTimestamp;
}
