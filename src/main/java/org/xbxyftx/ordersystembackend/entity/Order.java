// Order.java
package org.xbxyftx.ordersystembackend.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false) // 外键字段规范 [[7]]
    private Long userId; // 不直接关联User实体，减少级联操作开销

    @Column(columnDefinition = "TEXT") // JSON存储菜品信息 [[8]]
    private String dishInfo;

    @Column(nullable = false)
    private Integer status; // 建议使用枚举包装类

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}