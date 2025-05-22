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
    // 注入UserMapper
    @Autowired
    private UserMapper userMapper;
    
    // 注入PasswordEncoder
    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注册用户
    @Override
    public User register(UserDTO userDTO) {
        // 检查用户名是否已存在
        if (userMapper.findByUsername(userDTO.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }

        // 创建用户
        User user = new User();
        user.setUsername(userDTO.getUsername());
        // 对密码进行加密
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        
        // 插入用户
        userMapper.insert(user);
        return user;
    }

    // 用户登录
    @Override
    public User login(String username, String password) {
        // 根据用户名查询用户
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        // 比较密码是否匹配
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BusinessException("密码错误");
        }

        return user;
    }

    // 根据id获取用户
    @Override
    public User getUserById(Long id) {
        // 根据id从数据库中查找用户
        User user = userMapper.findById(id);
        // 如果用户不存在，抛出异常
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        // 返回用户
        return user;
    }
} 