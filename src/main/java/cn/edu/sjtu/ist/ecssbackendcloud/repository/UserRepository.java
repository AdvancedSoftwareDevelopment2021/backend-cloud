package cn.edu.sjtu.ist.ecssbackendcloud.repository;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.UserPO;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

/**
 * @brief 用户类repo类
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
public interface UserRepository extends MongoRepository<UserPO, String> {

    UserPO findUserPOById(String userId);

    UserPO findUserPOByUsername(String username);

    List<UserPO> findAll();
}
