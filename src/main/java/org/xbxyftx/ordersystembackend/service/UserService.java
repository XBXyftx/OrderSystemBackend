package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.dto.UserDTO;
import org.xbxyftx.ordersystembackend.entity.User;

public interface UserService {
    // 注册用户
    User register(UserDTO userDTO);
    // 登录
    User login(String username, String password);
    // 根据id获取用户
    User getUserById(Long id);
} 