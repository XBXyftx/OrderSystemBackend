// OrderOperation.java
package org.xbxyftx.ordersystembackend.entity;

import java.time.LocalDateTime;

public class OrderOperation {
    // 订单操作ID
    private Long id;
    // 操作人ID
    private Long operatorId;
    // 订单ID
    private Long orderId;
    // 操作类型
    private String operationType;
    // 操作内容
    private String operationContent;
    // 创建时间
    private LocalDateTime createdAt;

    // 获取订单操作ID
    public Long getId() { return id; }
    // 设置订单操作ID
    public void setId(Long id) { this.id = id; }
    
    // 获取操作人ID
    public Long getOperatorId() { return operatorId; }
    // 设置操作人ID
    public void setOperatorId(Long operatorId) { this.operatorId = operatorId; }
    
    // 获取订单ID
    public Long getOrderId() { return orderId; }
    // 设置订单ID
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    // 获取操作类型
    public String getOperationType() { return operationType; }
    // 设置操作类型
    public void setOperationType(String operationType) { this.operationType = operationType; }
    
    // 获取操作内容
    public String getOperationContent() { return operationContent; }
    // 设置操作内容
    public void setOperationContent(String operationContent) { this.operationContent = operationContent; }
    
    // 获取创建时间
    public LocalDateTime getCreatedAt() { return createdAt; }
    // 设置创建时间
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}