// OrderOperation.java
package org.xbxyftx.ordersystembackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "order_operations")
@Data
public class OrderOperation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "operator_id", nullable = false)
    private Long operatorId; // 操作者ID（用户ID）

    @Column(name = "order_id", nullable = false)
    private Long orderId; // 关联订单ID

    @Column(name = "operation_type", length = 50, nullable = false) // 操作类型约束 [[5]]
    private String operationType;

    @Column(name = "operation_content", columnDefinition = "TEXT", nullable = false)
    private String operationContent; // 操作内容存储JSON

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}