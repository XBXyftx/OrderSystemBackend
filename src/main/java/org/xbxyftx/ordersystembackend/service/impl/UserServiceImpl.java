package org.xbxyftx.ordersystembackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xbxyftx.ordersystembackend.dto.UserDTO;
import org.xbxyftx.ordersystembackend.entity.User;
import org.xbxyftx.ordersystembackend.exception.BusinessException;
import org.xbxyftx.ordersystembackend.mapper.UserMapper;
import org.xbxyftx.ordersystembackend.service.UserService;
import org.xbxyftx.ordersystembackend.utils.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User register(UserDTO userDTO) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(userDTO.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }

        // 创建用户
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        userMapper.insert(user);
        return user;
    }

    @Override
    public User login(String username, String password) {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user = userMapper.findById(id);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }
} 