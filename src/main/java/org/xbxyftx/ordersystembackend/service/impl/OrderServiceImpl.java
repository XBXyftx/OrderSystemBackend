package org.xbxyftx.ordersystembackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import org.xbxyftx.ordersystembackend.exception.BusinessException;
import org.xbxyftx.ordersystembackend.mapper.OrderMapper;
import org.xbxyftx.ordersystembackend.service.OrderService;
import org.xbxyftx.ordersystembackend.service.OrderOperationService;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderOperationService orderOperationService;

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setDishInfo(orderDTO.getDishInfo());
        order.setStatus(0); // 初始状态：待支付
        
        orderMapper.insert(order);
        
        // 记录订单创建操作
        orderOperationService.recordOperation(
            orderDTO.getUserId(),
            order.getId(),
            "CREATE",
            "创建订单"
        );
        
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderMapper.findById(id);
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderMapper.findByUserId(userId);
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        Order order = getOrderById(id);
        orderMapper.updateStatus(id, status);
        
        // 记录状态更新操作
        orderOperationService.recordOperation(
            order.getUserId(),
            id,
            "UPDATE_STATUS",
            "更新订单状态为: " + status
        );
    }
} 