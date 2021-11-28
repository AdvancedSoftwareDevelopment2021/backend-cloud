package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author dyanjun
 * @date 2021/11/28 14:48
 */
@Data
public class DataPackage {

    private String id;

    private String edgeId;

    private Date timestamp;

    private MultipartFile dataPackageFile;
}
