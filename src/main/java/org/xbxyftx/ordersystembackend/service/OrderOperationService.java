package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import java.util.List;

public interface OrderOperationService {
    List<OrderOperation> getOrderOperations(Long orderId);
    void recordOperation(Long operatorId, Long orderId, String operationType, String operationContent);
    List<OrderOperation> getOrderOperationsByUserId(Long userId);
    List<OrderOperation> getOperationsByOperatorId(Long operatorId);
    void deleteByOrderId(Long orderId);
} 