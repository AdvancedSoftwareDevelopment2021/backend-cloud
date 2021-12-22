package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.annotation.AdminAccess;
import cn.edu.sjtu.ist.ecssbackendcloud.annotation.PassToken;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.UserDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.UserService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.UserUtil;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtil userUtil;

    /**
     * 注册新用户
     * @return User 新用户
     */
    @PassToken
    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody UserDTO userDTO) {
        User user = userUtil.convertDTO2Domain(userDTO);
        return ResultUtil.success(userService.register(user));
    }

    /**
     * 登录
     */
    @PassToken
    @PostMapping(value = "/login")
    public Result<?> login(@RequestBody UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        Assert.isTrue(username != null && !username.equals(""), "登录用户名为空");
        Assert.isTrue(password != null && !password.equals(""), "登录用户密码为空");
        return ResultUtil.success(userService.login(username, password));
    }

    /**
     * 更新用户信息
     * @param userId 用户id
     */
    @PutMapping(value = "/user/{userId}")
    public Result<?> updateUser(@PathVariable String userId, @RequestBody UserDTO updateDTO) {
        User user = userUtil.convertDTO2Domain(updateDTO);
        userService.updateUser(userId, user);
        return ResultUtil.success();
    }

    /**
     * 更新用户密码
     * @param userId 用户id
     */
    @PutMapping(value = "/user/{userId}/password")
    public Result<String> updateUserPassword(@PathVariable String userId,
            @RequestBody UserDTO updateDTO) {
        userService.updateUserPassword(userId, updateDTO.getPassword());
        return ResultUtil.success();
    }

    /**
     * 查询所有用户信息，仅限管理员
     * @return 所有用户信息
     */
    @AdminAccess
    @GetMapping(value = "/user")
    public Result<?> findUser() {
        List<UserDTO> res = userService.findUsers()
                .stream()
                .map(userUtil::convertDomain2DTO)
                .collect(Collectors.toList());
        return ResultUtil.success(res);
    }
}
