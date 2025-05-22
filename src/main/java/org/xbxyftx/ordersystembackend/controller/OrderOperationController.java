package org.xbxyftx.ordersystembackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xbxyftx.ordersystembackend.common.Result;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;
import org.xbxyftx.ordersystembackend.service.OrderOperationService;

import java.util.List;

@RestController
@RequestMapping("/api/order-operations")
public class OrderOperationController {
    // 注入OrderOperationService
    @Autowired
    private OrderOperationService orderOperationService;

    // 根据订单ID获取订单操作
    @GetMapping("/order/{orderId}")
    public Result<List<OrderOperation>> getOrderOperations(@PathVariable Long orderId) {
        // 调用OrderOperationService的getOrderOperations方法获取订单操作
        List<OrderOperation> operations = orderOperationService.getOrderOperations(orderId);
        // 返回结果
        return Result.success(operations);
    }

    // 根据用户ID获取订单操作
    @GetMapping("/user/{userId}")
    public Result<List<OrderOperation>> getOrderOperationsByUserId(@PathVariable Long userId) {
        // 调用OrderOperationService的getOrderOperationsByUserId方法获取订单操作
        List<OrderOperation> operations = orderOperationService.getOrderOperationsByUserId(userId);
        // 返回结果
        return Result.success(operations);
    }
} 