package org.xbxyftx.ordersystembackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 * 用于配置Redis连接和序列化方式
 */
@Configuration
public class RedisConfig {
    /**
     * 配置RedisTemplate
     * 用于操作Redis数据库的模板类
     *
     * @param connectionFactory Redis连接工厂，由Spring自动注入
     * @return RedisTemplate<String, Object> 配置好的RedisTemplate实例
     */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        // 创建RedisTemplate对象，指定key和value的类型
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        
        // 设置Redis连接工厂，用于创建Redis连接
        template.setConnectionFactory(connectionFactory);
        
        // 配置序列化器
        // 1. 设置key的序列化方式为String类型
        // 使用StringRedisSerializer可以保证key的可读性
        template.setKeySerializer(new StringRedisSerializer());
        
        // 2. 设置value的序列化方式为JSON
        // 使用GenericJackson2JsonRedisSerializer可以自动处理复杂对象
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        // 3. 设置hash key的序列化方式为String类型
        // 用于Redis的Hash数据结构
        template.setHashKeySerializer(new StringRedisSerializer());
        
        // 4. 设置hash value的序列化方式为JSON
        // 用于Redis的Hash数据结构中的value
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        
        // 初始化RedisTemplate
        // 确保所有属性设置完成
        template.afterPropertiesSet();
        
        return template;
    }
} 