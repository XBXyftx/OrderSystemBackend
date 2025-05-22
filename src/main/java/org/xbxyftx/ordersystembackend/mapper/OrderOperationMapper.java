package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import java.util.List;

@Mapper
public interface OrderOperationMapper {
    // 根据订单ID查询订单操作记录
    List<OrderOperation> findByOrderId(@Param("orderId") Long orderId);
    // 插入订单操作记录
    int insert(OrderOperation operation);
    // 根据用户ID查询订单操作记录
    List<OrderOperation> findByUserId(@Param("userId") Long userId);
    // 根据操作员ID查询订单操作记录
    List<OrderOperation> findByOperatorId(Long operatorId);
    // 根据订单ID删除订单操作记录
    void deleteByOrderId(Long orderId);
} 