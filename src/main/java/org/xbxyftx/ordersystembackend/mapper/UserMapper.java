package org.xbxyftx.ordersystembackend.mapper;



import org.apache.ibatis.annotations.*;
import org.xbxyftx.ordersystembackend.entity.User;


import java.util.List;

@Mapper
public interface UserMapper {

    // 注解方式实现基础操作
    @Insert("INSERT INTO users(username,password) VALUES(#{username},#{password})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(User user); // 用户注册 [[7]]

    @Select("SELECT * FROM users WHERE id = #{id}")
    User findById(Long id); // 通过ID查询用户 [[2]]

    // XML方式实现动态查询
    @SelectProvider(type = UserSqlProvider.class, method = "findByCondition")
    List<User> findByCondition(String username, String password); // 动态条件查询 [[3]]
}