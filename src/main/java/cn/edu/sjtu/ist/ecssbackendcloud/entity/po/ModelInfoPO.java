package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Document("ModelInfo")
public class ModelInfoPO {

    @Id
    private String id;

    @Field
    private String name;

    @Field
    private String owner;

    @Field
    private String description;

    @Field
    private TimeUnit timeUnit;

    @Field
    private Integer interval;

    @Field
    private MultipartFile file;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date registerTimestamp;
}
