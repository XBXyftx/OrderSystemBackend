package org.xbxyftx.ordersystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@org.springframework.context.annotation.Configuration
public class SecurityConfig {
    /**
     * 配置Spring Security的过滤器链
     * 这个Bean定义了应用程序的安全规则和认证流程
     *
     * @param http HttpSecurity对象，用于配置Web安全
     * @return SecurityFilterChain 安全过滤器链
     * @throws Exception 配置过程中可能抛出的异常
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors() // 启用CORS（跨域资源共享）支持
                .and()
                .csrf().disable() // 禁用CSRF（跨站请求伪造）保护
                .authorizeRequests() // 开始配置URL访问权限
                .anyRequest().permitAll(); // 允许所有请求通过，不需要认证
        return http.build(); // 构建并返回安全过滤器链
    }
}