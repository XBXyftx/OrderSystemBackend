package org.xbxyftx.ordersystembackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import org.xbxyftx.ordersystembackend.mapper.OrderOperationMapper;
import org.xbxyftx.ordersystembackend.service.OrderOperationService;

import java.util.List;

@Service
public class OrderOperationServiceImpl implements OrderOperationService {
    @Autowired
    private OrderOperationMapper orderOperationMapper;

    @Override
    public List<OrderOperation> getOrderOperations(Long orderId) {
        return orderOperationMapper.findByOrderId(orderId);
    }

    @Override
    public void recordOperation(Long operatorId, Long orderId, String operationType, String operationContent) {
        OrderOperation operation = new OrderOperation();
        operation.setOperatorId(operatorId);
        operation.setOrderId(orderId);
        operation.setOperationType(operationType);
        operation.setOperationContent(operationContent);
        
        orderOperationMapper.insert(operation);
    }
} 