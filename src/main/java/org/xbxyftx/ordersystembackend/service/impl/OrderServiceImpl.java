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
        // 记录日志，开始创建订单
        logger.info("[createOrder] 开始创建订单，订单信息：{}", orderDTO);
        // 判断订单信息是否为空
        if (orderDTO == null) {
            throw new BusinessException("订单信息不能为空");
        }
        // 判断用户ID是否为空
        if (orderDTO.getUserId() == null) {
            throw new BusinessException("用户ID不能为空");
        }
        // 判断菜品信息是否为空
        if (orderDTO.getDishInfo() == null || orderDTO.getDishInfo().trim().isEmpty()) {
            throw new BusinessException("菜品信息不能为空");
        }
        // 创建订单对象
        Order order = new Order();
        // 设置订单的用户ID和菜品信息
        order.setUserId(orderDTO.getUserId());
        order.setDishInfo(orderDTO.getDishInfo());
        // 设置订单的初始状态为待支付
        order.setStatus(0); // 初始状态：待支付
        try {
            // 记录日志，准备插入订单
            logger.info("[createOrder] 准备插入订单，订单对象：{}", order);
            // 插入订单
            int rows = orderMapper.insert(order);
            // 记录日志，订单插入结果
            logger.info("[createOrder] 订单插入结果：受影响行数={}, 订单ID={}", rows, order.getId());
            // 判断插入结果是否为0行
            if (rows == 0) {
                throw new BusinessException("订单创建失败：数据库插入返回0行");
            }
            // 判断订单ID是否为空
            if (order.getId() == null) {
                throw new BusinessException("订单创建失败：未获取到订单ID");
            }
            // 记录订单创建操作
            try {
                // 记录日志，开始记录订单操作
                logger.info("[createOrder] 开始记录订单操作，订单ID={}, 用户ID={}", order.getId(), orderDTO.getUserId());
                // 调用订单操作服务记录订单操作
                orderOperationService.recordOperation(
                    orderDTO.getUserId(),
                    order.getId(),
                    "CREATE",
                    "创建订单"
                );
                // 记录日志，订单操作记录创建成功
                logger.info("[createOrder] 订单操作记录创建成功");
            } catch (Exception e) {
                // 记录日志，创建订单操作记录失败
                logger.error("[createOrder] 创建订单操作记录失败", e);
            }
            // 验证订单是否真的插入成功
            // 记录日志，验证订单是否插入成功
            logger.info("[createOrder] 验证订单是否插入成功，订单ID={}", order.getId());
            // 查询订单
            Order savedOrder = orderMapper.findById(order.getId());
            // 记录日志，订单保存后查询结果
            logger.info("[createOrder] 订单保存后查询结果：{}", savedOrder);
            // 判断查询结果是否为空
            if (savedOrder == null) {
                throw new BusinessException("订单创建失败：无法查询到创建的订单");
            }
            // 验证订单列表查询
            // 记录日志，验证用户订单列表查询
            logger.info("[createOrder] 验证用户订单列表查询，用户ID={}", orderDTO.getUserId());
            // 查询用户订单列表
            List<Order> userOrders = orderMapper.findByUserId(orderDTO.getUserId());
            // 记录日志，用户订单列表查询结果
            logger.info("[createOrder] 用户订单列表查询结果：{}", userOrders);
            // 返回订单
            return order;
        } catch (Exception e) {
            // 记录日志，订单创建异常
            logger.error("[createOrder] 订单创建异常", e);
            e.printStackTrace();
            // 抛出业务异常
            throw new BusinessException("订单创建失败：" + e.getMessage());
        }
    }

    @Override
    public Order getOrderById(Long id) {
        // 判断订单ID是否为空
        if (id == null) {
            throw new BusinessException("订单ID不能为空");
        }
        
        // 记录日志，开始查询订单
        logger.info("开始查询订单，订单ID={}", id);
        // 查询订单
        Order order = orderMapper.findById(id);
        // 记录日志，订单查询结果
        logger.info("订单查询结果：{}", order);
        
        // 判断查询结果是否为空
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        // 返回订单
        return order;
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        // 判断用户ID是否为空
        if (userId == null) {
            throw new BusinessException("用户ID不能为空");
        }
        
        // 记录日志，开始查询用户订单列表
        logger.info("开始查询用户订单列表，用户ID={}", userId);
        // 查询用户订单列表
        List<Order> orders = orderMapper.findByUserId(userId);
        // 记录日志，用户订单列表查询结果
        logger.info("用户订单列表查询结果：{}", orders);
        
        // 返回订单列表
        return orders;
    }

    @Override
    public void updateOrderStatus(Long id, Integer status) {
        // 记录日志，开始更新订单状态
        logger.info("开始更新订单状态，订单ID={}, 新状态={}", id, status);
        
        // 先查询订单是否存在
        // 记录日志，开始查询订单
        logger.info("开始查询订单，订单ID={}", id);
        // 查询订单
        Order order = orderMapper.findById(id);
        // 记录日志，订单查询结果
        logger.info("订单查询结果：{}", order);
        
        // 判断查询结果是否为空
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        // 更新订单状态
        int rows = orderMapper.updateStatus(id, status);
        // 记录日志，订单状态更新结果
        logger.info("订单状态更新结果：受影响行数={}", rows);
        
        // 判断更新结果是否为0行
        if (rows == 0) {
            throw new BusinessException("订单状态更新失败");
        }
        
        // 记录订单状态更新操作
        try {
            // 记录日志，开始记录订单状态更新操作
            logger.info("开始记录订单状态更新操作，订单ID={}, 用户ID={}", id, order.getUserId());
            // 调用订单操作服务记录订单状态更新操作
            orderOperationService.recordOperation(
                order.getUserId(),
                id,
                "UPDATE_STATUS",
                "更新订单状态为: " + status
            );
            // 记录日志，订单状态更新操作记录创建成功
            logger.info("订单状态更新操作记录创建成功");
        } catch (Exception e) {
            // 记录日志，记录订单状态更新操作失败
            logger.error("记录订单状态更新操作失败", e);
        }
    }

    @Override
    public void deleteOrder(Long id) {
        // 记录日志，开始删除订单
        logger.info("开始删除订单，订单ID={}", id);
        
        // 先查询订单是否存在
        // 记录日志，开始查询订单
        logger.info("开始查询订单，订单ID={}", id);
        // 查询订单
        Order order = orderMapper.findById(id);
        // 记录日志，订单查询结果
        logger.info("订单查询结果：{}", order);
        
        // 判断查询结果是否为空
        if (order == null) {
            throw new BusinessException("订单不存在");
        }
        
        try {
            // 先记录订单删除操作
            // 记录日志，开始记录订单删除操作
            logger.info("开始记录订单删除操作，订单ID={}, 用户ID={}", id, order.getUserId());
            // 调用订单操作服务记录订单删除操作
            orderOperationService.recordOperation(
                order.getUserId(),
                id,
                "DELETE",
                "删除订单"
            );
            // 记录日志，订单删除操作记录创建成功
            logger.info("订单删除操作记录创建成功");
            
            // 删除订单相关的操作记录
            // 记录日志，开始删除订单相关的操作记录
            logger.info("开始删除订单相关的操作记录，订单ID={}", id);
            // 调用订单操作服务删除订单相关的操作记录
            orderOperationService.deleteByOrderId(id);
            // 记录日志，订单相关的操作记录删除成功
            logger.info("订单相关的操作记录删除成功");
            
            // 删除订单
            int rows = orderMapper.deleteById(id);
            // 记录日志，订单删除结果
            logger.info("订单删除结果：受影响行数={}", rows);
            
            // 判断删除结果是否为0行
            if (rows == 0) {
                throw new BusinessException("订单删除失败");
            }
        } catch (Exception e) {
            // 记录日志，删除订单失败
            logger.error("删除订单失败", e);
            // 抛出业务异常
            throw new BusinessException("删除订单失败：" + e.getMessage());
        }
    }
} 