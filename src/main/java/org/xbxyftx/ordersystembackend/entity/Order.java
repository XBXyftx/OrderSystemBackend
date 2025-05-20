// Order.java
package org.xbxyftx.ordersystembackend.entity;

import java.time.LocalDateTime;
import java.util.List;
import org.xbxyftx.ordersystembackend.entity.OrderOperation;

public class Order {
    private Long id;
    private Long userId;
    private String dishInfo;
    private Integer status; // 0待支付 1已支付 2制作中 3配送中 4已完成
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<OrderOperation> operations;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getDishInfo() { return dishInfo; }
    public void setDishInfo(String dishInfo) { this.dishInfo = dishInfo; }
    
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public List<OrderOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<OrderOperation> operations) {
        this.operations = operations;
    }
}