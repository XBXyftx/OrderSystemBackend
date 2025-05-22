package org.xbxyftx.ordersystembackend.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {
    // 秘钥
    private final String secret = "your-secret-key";
    private final long expiration = 86400000; // 24小时

    public String generateToken(Long userId, String username) {
        // 使用Jwts.builder()创建一个JWT对象
        return Jwts.builder()
                // 设置JWT的主题为用户名
                .setSubject(username)
                // 添加一个自定义的声明，键为"userId"，值为userId
                .claim("userId", userId)
                // 设置JWT的签发时间为当前时间
                .setIssuedAt(new Date())
                // 设置JWT的过期时间为当前时间加上过期时间
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                // 使用HS256算法对JWT进行签名
                .signWith(SignatureAlgorithm.HS256, secret)
                // 将JWT对象转换为字符串
                .compact();
    }
} 