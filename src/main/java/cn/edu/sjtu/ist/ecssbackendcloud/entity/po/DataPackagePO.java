package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document(collation = "DataPackage")
public class DataPackagePO {

    @Id
    private String id;

    private String edgeId;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private MultipartFile dataPackageFile;

}
