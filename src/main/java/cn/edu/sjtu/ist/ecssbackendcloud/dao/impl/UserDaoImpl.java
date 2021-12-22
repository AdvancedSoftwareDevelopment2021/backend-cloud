package cn.edu.sjtu.ist.ecssbackendcloud.dao.impl;

import cn.edu.sjtu.ist.ecssbackendcloud.dao.UserDao;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.po.UserPO;
import cn.edu.sjtu.ist.ecssbackendcloud.repository.UserRepository;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.UserUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @brief 用户类daoImpl类
 * @author rsp
 * @version 0.1
 * @date 2021-12-21
 */
@Component
public class UserDaoImpl implements UserDao {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserUtil userUtil;

    @Override
    public User createUser(User user) {
        UserPO po = userUtil.convertDomain2PO(user);
        userRepository.save(po);
        po = userRepository.findUserPOByUsername(user.getUsername());
        return userUtil.convertPO2Domain(po);
    }

    @Override
    public void updateUser(String userId, User user) {
        user.setId(userId);
        userRepository.save(userUtil.convertDomain2PO(user));
    }

    @Override
    public User findUserById(String userId) {
        UserPO po = userRepository.findUserPOById(userId);
        return userUtil.convertPO2Domain(po);
    }

    @Override
    public User findUserByUsername(String username) {
        UserPO po = userRepository.findUserPOByUsername(username);
        return userUtil.convertPO2Domain(po);
    }

    @Override
    public List<User> findUsers() {
        return userRepository.findAll()
                            .stream()
                            .map(userUtil::convertPO2Domain)
                            .collect(Collectors.toList());
    }
}
