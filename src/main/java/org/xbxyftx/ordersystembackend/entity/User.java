// User.java
package org.xbxyftx.ordersystembackend.entity;
import lombok.Data;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class User implements Serializable { // 实现Serializable用于分布式场景 [[9]]
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50) // 对应数据库唯一约束 [[5]]
    private String username;

    @Column(nullable = false)
    private String password; // 推荐存储BCrypt加密值

    @Column(name = "created_at", updatable = false) // 时间字段规范 [[2]]
    private LocalDateTime createdAt;
}