package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import java.util.List;

@Mapper
public interface OrderOperationMapper {
    List<OrderOperation> findByOrderId(@Param("orderId") Long orderId);
    int insert(OrderOperation operation);
    List<OrderOperation> findByUserId(@Param("userId") Long userId);
    List<OrderOperation> findByOperatorId(Long operatorId);
    void deleteByOrderId(Long orderId);
} 