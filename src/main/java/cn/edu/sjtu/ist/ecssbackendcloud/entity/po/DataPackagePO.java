package cn.edu.sjtu.ist.ecssbackendcloud.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Id;
import java.util.Date;

@Data
@Document("DataPackage")
public class DataPackagePO {

    @Id
    private String id;

    private String name;

    private String edgeId;

    private String contentType; // 文件类型

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timestamp;

    private long size;

    private String md5;

    private Binary content; // 文件内容

}
