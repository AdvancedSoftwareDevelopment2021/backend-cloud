package cn.edu.sjtu.ist.ecssbackendcloud.service;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.UserDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @brief 用户类service类
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    /**
     * 注册新用户
     * @param user 用户
     * @return 新用户信息
     */
    public User register(User user) {
        return userDao.createUser(user);
    }

    /**
     * 更新用户信息
     * @param userId 用户id
     * @param user 更新的用户信息
     */
    public void updateUser(String userId, User user) {
        userDao.updateUser(userId, user);
    }

    /**
     * 更新用户密码
     * @param userId 用户id
     * @param newPasswd 新密码
     */
    public void updateUserPassword(String userId, String newPasswd) {
        User user = userDao.findUserById(userId);
        user.updatePasswd(newPasswd);
        userDao.updateUser(userId, user);
    }

    public User findUserById(String userId) {
        return userDao.findUserById(userId);
    }

    /**
     * 通过姓名查询用户
     * @return 用户信息
     */
    public User findUserByName(String username) {
        return userDao.findUserByUsername(username);
    }

    /**
     * 查询所有用户信息
     * @return 所有用户信息
     */
    public List<User> findUsers() {
        return userDao.findUsers();
    }

    public void addProcess(String userId, String processId) {
        User user = userDao.findUserById(userId);
        user.addProcess(processId);
        userDao.updateUser(userId, user);
    }

}
