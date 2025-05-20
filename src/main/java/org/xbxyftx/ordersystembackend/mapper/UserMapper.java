package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.User;

@Mapper
public interface UserMapper {
    User findById(@Param("id") Long id);
    User findByUsername(@Param("username") String username);
    int insert(User user);
    int update(User user);
}