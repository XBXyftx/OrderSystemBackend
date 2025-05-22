package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import java.util.List;

public interface OrderOperationService {
    // 根据订单id获取订单操作记录
    List<OrderOperation> getOrderOperations(Long orderId);
    // 记录操作
    void recordOperation(Long operatorId, Long orderId, String operationType, String operationContent);
    // 根据用户id获取订单操作记录
    List<OrderOperation> getOrderOperationsByUserId(Long userId);
    // 根据操作员id获取订单操作记录
    List<OrderOperation> getOperationsByOperatorId(Long operatorId);
    // 根据订单id删除订单操作记录
    void deleteByOrderId(Long orderId);
} 