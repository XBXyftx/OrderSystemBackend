// Order.java
package org.xbxyftx.ordersystembackend.entity;

import java.time.LocalDateTime;
import java.util.List;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;

public class Order {
    // 订单ID
    private Long id;
    // 用户ID
    private Long userId;
    // 菜品信息
    private String dishInfo;
    private Integer status; // 0待支付 1已支付 2制作中 3配送中 4已完成
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderOperation> operations;

    // 获取订单ID
    public Long getId() { return id; }
    // 设置订单ID
    public void setId(Long id) { this.id = id; }
    
    // 获取用户ID
    public Long getUserId() { return userId; }
    // 设置用户ID
    public void setUserId(Long userId) { this.userId = userId; }
    
    // 获取菜品信息
    public String getDishInfo() { return dishInfo; }
    // 设置菜品信息
    public void setDishInfo(String dishInfo) { this.dishInfo = dishInfo; }
    
    // 获取订单状态
    public Integer getStatus() { return status; }
    // 设置订单状态
    public void setStatus(Integer status) { this.status = status; }
    
    // 获取订单创建时间
    public LocalDateTime getCreatedAt() { return createdAt; }
    // 设置订单创建时间
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    // 获取订单更新时间
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    // 设置订单更新时间
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    // 获取订单操作列表
    public List<OrderOperation> getOperations() {
        return operations;
    }

    // 设置订单操作列表
    public void setOperations(List<OrderOperation> operations) {
        this.operations = operations;
    }
}