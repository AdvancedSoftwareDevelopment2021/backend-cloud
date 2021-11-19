package ist.sjtu.edu.cn.ecssbackendcloud.entity.po;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collation = "EdgeInfo")
public class EdgeInfo {

    @Id
    private String id;
}
