// OrderOperation.java
package org.xbxyftx.ordersystembackend.entity;

import java.time.LocalDateTime;

public class OrderOperation {
    private Long id;
    private Long operatorId;
    private Long orderId;
    private String operationType;
    private String operationContent;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public Long getOperatorId() { return operatorId; }
    public void setOperatorId(Long operatorId) { this.operatorId = operatorId; }
    
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    
    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }
    
    public String getOperationContent() { return operationContent; }
    public void setOperationContent(String operationContent) { this.operationContent = operationContent; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}