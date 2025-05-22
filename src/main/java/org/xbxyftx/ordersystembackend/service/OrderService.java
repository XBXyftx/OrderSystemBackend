package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import java.util.List;

public interface OrderService {
    // 创建订单
    Order createOrder(OrderDTO orderDTO);
    // 根据id获取订单
    Order getOrderById(Long id);
    // 根据用户id获取订单列表
    List<Order> getOrdersByUserId(Long userId);
    // 更新订单状态
    void updateOrderStatus(Long id, Integer status);
    // 删除订单
    void deleteOrder(Long id);
} 