package cn.edu.sjtu.ist.ecssbackendcloud.dao;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;

import java.util.List;

/**
 * @brief 用户类dao类
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
public interface UserDao {

    User createUser(User user);

    void updateUser(String userId, User user);

    User findUserById(String userId);

    User findUserByUsername(String username);

    List<User> findUsers();
}
