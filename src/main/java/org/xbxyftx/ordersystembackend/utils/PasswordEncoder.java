package org.xbxyftx.ordersystembackend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encode(String password) {
        // 使用encoder对象对密码进行编码
        return encoder.encode(password);
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        // 检查原始密码和编码密码是否匹配
        return encoder.matches(rawPassword, encodedPassword);
    }
} 