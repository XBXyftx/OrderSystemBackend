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
    @Autowired
    private OrderOperationService orderOperationService;

    @GetMapping("/order/{orderId}")
    public Result<List<OrderOperation>> getOrderOperations(@PathVariable Long orderId) {
        List<OrderOperation> operations = orderOperationService.getOrderOperations(orderId);
        return Result.success(operations);
    }
} 