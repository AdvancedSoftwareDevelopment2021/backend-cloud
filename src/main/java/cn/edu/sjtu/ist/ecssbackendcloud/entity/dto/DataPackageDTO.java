package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPackageDTO {


    private String id;

    private String name;

    private String edgeId;

    private String edgeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS", timezone = "GMT+8")
    private Date timestamp;

}