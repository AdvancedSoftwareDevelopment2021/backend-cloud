package ist.sjtu.edu.cn.ecssbackendcloud.entity.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "DataPackagePO")
public class DataPackagePO {

    @Id
    private String id;

    private String date;

    private String token;

}
