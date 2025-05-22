package org.xbxyftx.ordersystembackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xbxyftx.ordersystembackend.common.Result;
import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import org.xbxyftx.ordersystembackend.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    // 注入OrderService
    @Autowired
    private OrderService orderService;

    // 创建订单
    @PostMapping
    public Result<Order> createOrder(@RequestBody OrderDTO orderDTO) {
        // 调用OrderService的createOrder方法创建订单
        Order order = orderService.createOrder(orderDTO);
        // 返回创建成功的订单
        return Result.success(order);
    }

    // 根据id获取订单
    @GetMapping("/{id}")
    public Result<Order> getOrderById(@PathVariable Long id) {
        // 调用OrderService的getOrderById方法根据id获取订单
        Order order = orderService.getOrderById(id);
        // 返回获取的订单
        return Result.success(order);
    }

    // 根据用户id获取订单列表
    @GetMapping("/user/{userId}")
    public Result<List<Order>> getOrdersByUserId(@PathVariable Long userId) {
        // 调用OrderService的getOrdersByUserId方法根据用户id获取订单列表
        List<Order> orders = orderService.getOrdersByUserId(userId);
        // 返回获取的订单列表
        return Result.success(orders);
    }

    // 更新订单状态
    @PutMapping("/{id}/status")
    public Result<Void> updateOrderStatus(@PathVariable Long id, @RequestParam Integer status) {
        // 调用OrderService的updateOrderStatus方法更新订单状态
        orderService.updateOrderStatus(id, status);
        // 返回更新成功的状态
        return Result.success(null);
    }

    // 删除订单
    @DeleteMapping("/{id}")
    public Result<Void> deleteOrder(@PathVariable Long id) {
        // 调用OrderService的deleteOrder方法删除订单
        orderService.deleteOrder(id);
        // 返回删除成功的状态
        return Result.success(null);
    }
} 