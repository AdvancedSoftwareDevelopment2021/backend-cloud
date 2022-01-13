package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

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
public class ModelEdgeDTO {

    private String id;

    private String name;

}
