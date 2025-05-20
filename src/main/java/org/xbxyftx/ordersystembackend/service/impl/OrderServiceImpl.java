package org.xbxyftx.ordersystembackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.xbxyftx.ordersystembackend.dto.OrderDTO;
import org.xbxyftx.ordersystembackend.entity.Order;
import org.xbxyftx.ordersystembackend.exception.BusinessException;
import org.xbxyftx.ordersystembackend.mapper.OrderMapper;
import org.xbxyftx.ordersystembackend.service.OrderService;
import org.xbxyftx.ordersystembackend.service.OrderOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
    
    @Autowired
    private OrderMapper orderMapper;
    
    @Autowired
    private OrderOperationService orderOperationService;

    @Override
    public Order createOrder(OrderDTO orderDTO) {
        logger.info("[createOrder] 开始创建订单，订单信息：{}", orderDTO);
        if (orderDTO == null) {
            throw new BusinessException("订单信息不能为空");
        }
        if (orderDTO.getUserId() == null) {
            throw new BusinessException("用户ID不能为空");
        }
        if (orderDTO.getDishInfo() == null || orderDTO.getDishInfo().trim().isEmpty()) {
            throw new BusinessException("菜品信息不能为空");
        }
        Order order = new Order();
        order.setUserId(orderDTO.getUserId());
        order.setDishInfo(orderDTO.getDishInfo());
        order.setStatus(0); // 初始状态：待支付
        try {
            logger.info("[createOrder] 准备插入订单，订单对象：{}", order);
            int rows = orderMapper.insert(order);
            logger.info("[createOrder] 订单插入结果：受影响行数={}, 订单ID={}", rows, order.getId());
            if (rows == 0) {
                throw new BusinessException("订单创建失败：数据库插入返回0行");
            }
            if (order.getId() == null) {
                throw new BusinessException("订单创建失败：未获取到订单ID");
            }
            // 记录订单创建操作
            try {
                logger.info("[createOrder] 开始记录订单操作，订单ID={}, 用户ID={}", order.getId(), orderDTO.getUserId());
                orderOperationService.recordOperation(
                    orderDTO.getUserId(),
                    order.getId(),
                    "CREATE",
                    "创建订单"
                );
                logger.info("[createOrder] 订单操作记录创建成功");
            } catch (Exception e) {
                logger.error("[createOrder] 创建订单操作记录失败", e);
            }
            // 验证订单是否真的插入成功
            logger.info("[createOrder] 验证订单是否插入成功，订单ID={}", order.getId());
            Order savedOrder = orderMapper.findById(order.getId());
            logger.info("[createOrder] 订单保存后查询结果：{}", savedOrder);
            if (savedOrder == null) {
                throw new BusinessException("订单创建失败：无法查询到创建的订单");
            }
            // 验证订单列表查询
            logger.info("[createOrder] 验证用户订单列表查询，用户ID={}", orderDTO.getUserId());
            List<Order> userOrders = orderMapper.findByUserId(orderDTO.getUserId());
            logger.info("[createOrder] 用户订单列表查询结果：{}", userOrders);
            return order;
        } catch (Exception e) {
            logger.error("[createOrder] 订单创建异常", e);
            e.printStackTrace();
            throw new BusinessException("订单创建失败：" + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(Long id) {
        if (id == null) {
            throw new BusinessException("订单ID不能为空");
        }
        
        logger.info("开始查询订单，订单ID={}", id);
        Order order = orderMapper.findById(id);
        logger.info("订单查询结果：{}", order);
        
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        
        logger.info("开始查询用户订单列表，用户ID={}", userId);
        List<Order> orders = orderMapper.findByUserId(userId);
        logger.info("用户订单列表查询结果：{}", orders);
        
        return orders;
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        if (id == null) {
            throw new BusinessException("订单ID不能为空");
        }
        if (status == null) {
            throw new BusinessException("订单状态不能为空");
        }
        
        logger.info("开始更新订单状态，订单ID={}, 新状态={}", id, status);
        Order order = getOrderById(id);
        int rows = orderMapper.updateStatus(id, status);
        logger.info("订单状态更新结果：受影响行数={}", rows);
        
        if (rows == 0) {
            throw new BusinessException("订单状态更新失败：数据库更新返回0行");
        }
        
        // 记录状态更新操作
        try {
            logger.info("开始记录订单状态更新操作，订单ID={}, 用户ID={}", id, order.getUserId());
            orderOperationService.recordOperation(
                order.getUserId(),
                id,
                "UPDATE_STATUS",
                "更新订单状态为: " + status
            );
            logger.info("订单状态更新操作记录创建成功");
        } catch (Exception e) {
            logger.error("创建订单状态更新操作记录失败", e);
            // 这里不抛出异常，因为订单状态已经更新成功
        }
    }
} 