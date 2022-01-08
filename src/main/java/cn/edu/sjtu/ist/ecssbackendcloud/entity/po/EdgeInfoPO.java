package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@Document("EdgeInfo")
public class EdgeInfoPO {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String owner;

    @Field
    private String description;

    @Field
    private String ip;

    @Field
    private Integer port;

    @Field
    private String api;

    @Field
    private TimeUnit timeUnit;

    @Field
    private Integer interval;

    @Field
    private EdgeStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date registerTimestamp;
}
