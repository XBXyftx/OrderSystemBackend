package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.User;

@Mapper
public interface UserMapper {
    // 根据id查询用户
    User findById(@Param("id") Long id);
    // 根据用户名查询用户
    User findByUsername(@Param("username") String username);
    // 插入用户
    int insert(User user);
    // 更新用户
    int update(User user);
}