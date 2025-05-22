package org.xbxyftx.ordersystembackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xbxyftx.ordersystembackend.common.Result;
import org.xbxyftx.ordersystembackend.dto.UserDTO;
import org.xbxyftx.ordersystembackend.entity.User;
import org.xbxyftx.ordersystembackend.service.UserService;
import org.xbxyftx.ordersystembackend.utils.JwtUtil;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // 注入UserService
    @Autowired
    private UserService userService;

    // 注入JwtUtil
    @Autowired
    private JwtUtil jwtUtil;

    // 注册接口
    @PostMapping("/register")
    public Result<User> register(@RequestBody UserDTO userDTO) {
        // 调用UserService的register方法进行注册
        User user = userService.register(userDTO);
        // 返回注册结果
        return Result.success(user);
    }

    // 登录接口
    @PostMapping("/login")
    public Result<User> login(@RequestBody UserDTO userDTO) {
        // 调用UserService的login方法进行登录
        User user = userService.login(userDTO.getUsername(), userDTO.getPassword());
        // 返回登录结果
        return Result.success(user);
    }

    // 根据id获取用户信息接口
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        // 调用UserService的getUserById方法获取用户信息
        User user = userService.getUserById(id);
        // 返回用户信息
        return Result.success(user);
    }
} 