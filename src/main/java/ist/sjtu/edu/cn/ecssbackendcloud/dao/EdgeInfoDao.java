package ist.sjtu.edu.cn.ecssbackendcloud.dao;

import com.sun.javafx.geom.Edge;
import ist.sjtu.edu.cn.ecssbackendcloud.dto.EdgeInfoDto;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdgeInfoDao extends MongoRepository<EdgeInfoDto, Long> {
}
