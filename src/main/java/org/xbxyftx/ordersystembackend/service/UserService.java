package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.dto.UserDTO;
import org.xbxyftx.ordersystembackend.entity.User;

public interface UserService {
    User register(UserDTO userDTO);
    User login(String username, String password);
    User getUserById(Long id);
} 