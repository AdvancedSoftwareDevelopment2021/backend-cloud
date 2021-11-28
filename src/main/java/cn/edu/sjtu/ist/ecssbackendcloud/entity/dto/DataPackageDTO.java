package cn.edu.sjtu.ist.ecssbackendcloud.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataPackageDTO {

    private String id;

    private String filename;

    private String edgeId;

    private String edgeName;

    private Date timestamp;

}