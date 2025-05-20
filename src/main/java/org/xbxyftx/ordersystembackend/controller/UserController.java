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
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public Result<User> register(@RequestBody UserDTO userDTO) {
        User user = userService.register(userDTO);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result<User> login(@RequestBody UserDTO userDTO) {
        User user = userService.login(userDTO.getUsername(), userDTO.getPassword());
        return Result.success(user);
    }

    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }
} 