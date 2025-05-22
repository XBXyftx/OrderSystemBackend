package org.xbxyftx.ordersystembackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import org.xbxyftx.ordersystembackend.mapper.OrderOperationMapper;
import org.xbxyftx.ordersystembackend.service.OrderOperationService;

import java.util.List;

@Service
public class OrderOperationServiceImpl implements OrderOperationService {
    // 注入OrderOperationMapper
    @Autowired
    private OrderOperationMapper orderOperationMapper;

    // 根据订单id获取订单操作记录
    @Override
    public List<OrderOperation> getOrderOperations(Long orderId) {
        return orderOperationMapper.findByOrderId(orderId);
    }

    // 记录订单操作
    @Override
    public void recordOperation(Long operatorId, Long orderId, String operationType, String operationContent) {
        // 创建订单操作对象
        OrderOperation operation = new OrderOperation();
        // 设置操作人ID
        operation.setOperatorId(operatorId);
        // 设置订单ID
        operation.setOrderId(orderId);
        // 设置操作类型
        operation.setOperationType(operationType);
        // 设置操作内容
        operation.setOperationContent(operationContent);
        
        // 将订单操作对象插入数据库
        orderOperationMapper.insert(operation);
    }

    // 根据用户id获取订单操作记录
    @Override
    public List<OrderOperation> getOrderOperationsByUserId(Long userId) {
        // 调用orderOperationMapper的findByUserId方法，根据用户id查询订单操作记录
        return orderOperationMapper.findByUserId(userId);
    }

    // 根据操作人id获取订单操作记录
    @Override
    public List<OrderOperation> getOperationsByOperatorId(Long operatorId) {
        // 调用orderOperationMapper的findByOperatorId方法，根据操作人id获取订单操作记录
        return orderOperationMapper.findByOperatorId(operatorId);
    }
    
    // 根据订单id删除订单操作记录
    @Override
    public void deleteByOrderId(Long orderId) {
        // 调用orderOperationMapper的deleteByOrderId方法，根据订单id删除订单操作记录
        orderOperationMapper.deleteByOrderId(orderId);
    }
} 