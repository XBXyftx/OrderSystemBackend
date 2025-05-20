package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.Order;
//import org.xmlunit.util.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    /**
     * 根据用户ID查询订单列表
     */
    List<Order> findByUserId(@Param("userId") Long userId);

    /**
     * 根据订单ID查询订单
     */
    Order findById(@Param("id") Long id);

    /**
     * 插入新订单
     * @return 受影响的行数
     */
    int insert(Order order);

    /**
     * 更新订单状态
     * @return 受影响的行数
     */
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}