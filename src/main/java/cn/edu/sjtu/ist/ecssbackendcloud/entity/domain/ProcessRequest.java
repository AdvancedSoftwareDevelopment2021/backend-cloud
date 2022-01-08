package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author dyanjun
 * @date 2022/1/8 20:37
 */
@Data
public class ProcessRequest {
    String name;
    String owner;
    MultipartFile file;
}
