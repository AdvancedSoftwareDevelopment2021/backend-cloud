package cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.process;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bson.types.Binary;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author dyanjun
 * @date 2021/11/28 14:48
 */
@Data
public class DataPackage {

    private String id;

    private String name;

    private String edgeId;

    private String contentType; // 文件类型

    private Date timestamp;

    private long size;

    private String md5;

    private Binary content; // 文件内容
}
