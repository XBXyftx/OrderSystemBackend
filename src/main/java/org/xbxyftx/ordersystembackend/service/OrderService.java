package org.xbxyftx.ordersystembackend.service;

import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import java.util.List;

public interface OrderService {
    Order createOrder(OrderDTO orderDTO);
    Order getOrderById(Long id);
    List<Order> getOrdersByUserId(Long userId);
    void updateOrderStatus(Long id, Integer status);
} 