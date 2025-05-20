package org.xbxyftx.ordersystembackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import org.xbxyftx.ordersystembackend.exception.BusinessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void testCreateOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setDishInfo("{\"dishes\":[{\"id\":1,\"name\":\"宫保鸡丁\",\"price\":28}]}");

        Order order = orderService.createOrder(orderDTO);
        assertNotNull(order);
        assertNotNull(order.getId());
        assertEquals(1L, order.getUserId());
        assertEquals(0, order.getStatus()); // 初始状态为待支付
    }

    @Test
    public void testGetOrderById() {
        // 先创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setDishInfo("{\"dishes\":[{\"id\":1,\"name\":\"宫保鸡丁\",\"price\":28}]}");
        Order createdOrder = orderService.createOrder(orderDTO);

        // 测试查询
        Order order = orderService.getOrderById(createdOrder.getId());
        assertNotNull(order);
        assertEquals(createdOrder.getId(), order.getId());
    }

    @Test
    public void testUpdateOrderStatus() {
        // 先创建订单
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(1L);
        orderDTO.setDishInfo("{\"dishes\":[{\"id\":1,\"name\":\"宫保鸡丁\",\"price\":28}]}");
        Order createdOrder = orderService.createOrder(orderDTO);

        // 更新状态
        orderService.updateOrderStatus(createdOrder.getId(), 1);

        // 验证状态
        Order order = orderService.getOrderById(createdOrder.getId());
        assertEquals(1, order.getStatus());
    }
} 