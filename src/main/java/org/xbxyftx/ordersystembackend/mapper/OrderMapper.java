package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.Order;
//import org.xmlunit.util.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {

    // 注解方式实现基础操作
    @Insert("INSERT INTO orders(user_id,dish_info,status) VALUES(#{userId},#{dishInfo},#{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void createOrder(Order order); // 创建订单 [[7]]

    // 注解方式实现关联查询
    @Select("SELECT * FROM orders WHERE user_id = #{userId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "dishInfo", column = "dish_info"),
            @Result(property = "operations", column = "id",
                    many = @Many(select = "org.xbxyftx.ordersystem.mapper.OrderOperationMapper.findByOrderId"))
    })
    List<Order> findByUserId(@Param("userId") Long userId); // 查询用户订单及关联操作记录 [[1]]

    // XML方式实现状态更新
    @UpdateProvider(type = OrderSqlProvider.class, method = "updateStatus")
    void updateStatus(Order order); // 动态更新订单状态 [[3]]

    Order findById(@Param("id") Long id);
    int insert(Order order);
    int updateStatus(@Param("id") Long id, @Param("status") Integer status);
}