package org.xbxyftx.ordersystembackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 重写addCorsMappings方法，配置跨域请求
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 添加映射，允许所有路径
        registry.addMapping("/**")
                // 允许的源
                .allowedOrigins("http://localhost:5500", "http://127.0.0.1:5500")
                // 允许的请求方法
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // 允许的请求头
                .allowedHeaders("*")
                // 是否允许发送Cookie
                .allowCredentials(false);
    }
}
