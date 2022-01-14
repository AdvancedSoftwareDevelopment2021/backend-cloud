package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.EdgeStatus;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.TimeUnit;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelInfoDTO {

    private String id;

    private String name;

    private String owner;

    private String description;

    private TimeUnit timeUnit;

    private Integer interval;

    private String status = "normal";

    private MultipartFile modelFile;

    private String train;

    private MultipartFile scriptFile;

    private MultipartFile trainScriptFile;

    private MultipartFile predictScriptFile;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date registerTimestamp;
}
