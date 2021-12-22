package cn.edu.sjtu.ist.ecssbackendcloud.controller;

import cn.edu.sjtu.ist.ecssbackendcloud.entity.domain.User;
import cn.edu.sjtu.ist.ecssbackendcloud.entity.dto.UserDTO;
import cn.edu.sjtu.ist.ecssbackendcloud.service.UserService;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.convert.UserUtil;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.Result;
import cn.edu.sjtu.ist.ecssbackendcloud.utils.response.ResultUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping(value = "/register")
    public Result<?> register(@RequestBody UserDTO userDTO) {
        log.info(userDTO.toString());
        User user = userUtil.convertDTO2Domain(userDTO);
        return ResultUtil.success(userService.register(user));
    }

    /**
     * 更新用户信息
     * @param userId 用户id
     */
    @PutMapping(value = "/user/{userId}")
    public Result<?> updateUser(@PathVariable String userId,
            @RequestBody UserDTO updateDTO) {
        User user = userUtil.convertDTO2Domain(updateDTO);
        userService.updateUser(userId, user);
        return ResultUtil.success();
    }

    /**
     * 更新用户密码
     * 
     * @param userId 用户id
     */
    @PutMapping(value = "/user/{userId}/password")
    public Result<String> updateUserPassword(@PathVariable String userId,
            @RequestBody UserDTO updateDTO) {
        userService.updateUserPassword(userId, updateDTO.getPassword());
        return ResultUtil.success();
    }

    /**
     * 查询所有用户信息
     * 
     * @return 所有用户信息
     */
    @GetMapping(value = "/user")
    public Result<?> findUser() {
        List<UserDTO> res = userService.findUsers()
                .stream()
                .map(userUtil::convertDomain2DTO)
                .collect(Collectors.toList());
        return ResultUtil.success(res);
    }
}
