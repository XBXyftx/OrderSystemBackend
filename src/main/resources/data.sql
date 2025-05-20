-- 用户表 (users)
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
                       username VARCHAR(50) UNIQUE NOT NULL COMMENT '用户名',
                       password VARCHAR(100) NOT NULL COMMENT '密码',
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 订单表 (orders)
CREATE TABLE orders (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
                        user_id BIGINT NOT NULL COMMENT '下单用户ID',
                        dish_info TEXT NOT NULL COMMENT '菜品信息（JSON格式）',
                        status TINYINT DEFAULT 0 COMMENT '订单状态: 0-待支付 1-已支付 2-制作中 3-配送中 4-已完成',
                        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '下单时间',
                        FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                        INDEX idx_user_id(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单信息表';

-- 订单操作记录表 (order_operations)
CREATE TABLE order_operations (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '操作记录ID',
                                  operator_id BIGINT NOT NULL COMMENT '操作者ID',
                                  order_id BIGINT NOT NULL COMMENT '关联订单ID',
                                  operation_type VARCHAR(50) NOT NULL COMMENT '操作类型（修改状态/备注等）',
                                  operation_content TEXT NOT NULL COMMENT '操作内容详情',
                                  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
                                  FOREIGN KEY (operator_id) REFERENCES users(id) ON DELETE CASCADE,
                                  FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
                                  INDEX idx_order_id(order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单操作记录表';