-- 检查数据库是否存在，不存在则创建
CREATE DATABASE IF NOT EXISTS order_system DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE order_system;

-- 检查并创建用户表
CREATE TABLE IF NOT EXISTS users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 检查并创建订单表
CREATE TABLE IF NOT EXISTS orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    dish_info TEXT NOT NULL,
    status TINYINT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 检查并创建订单操作记录表
CREATE TABLE IF NOT EXISTS order_operations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    operator_id BIGINT NOT NULL,
    order_id BIGINT NOT NULL,
    operation_type VARCHAR(50) NOT NULL,
    operation_content TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (operator_id) REFERENCES users(id),
    FOREIGN KEY (order_id) REFERENCES orders(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建索引（使用动态SQL来避免重复创建）
DELIMITER //
CREATE PROCEDURE create_index_if_not_exists()
BEGIN
    -- 检查并创建orders表的索引
    IF NOT EXISTS (SELECT 1 FROM information_schema.statistics 
                  WHERE table_schema = 'order_system' 
                  AND table_name = 'orders' 
                  AND index_name = 'idx_orders_user_id') THEN
        CREATE INDEX idx_orders_user_id ON orders(user_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.statistics 
                  WHERE table_schema = 'order_system' 
                  AND table_name = 'orders' 
                  AND index_name = 'idx_orders_status') THEN
        CREATE INDEX idx_orders_status ON orders(status);
    END IF;
    
    -- 检查并创建order_operations表的索引
    IF NOT EXISTS (SELECT 1 FROM information_schema.statistics 
                  WHERE table_schema = 'order_system' 
                  AND table_name = 'order_operations' 
                  AND index_name = 'idx_order_operations_order_id') THEN
        CREATE INDEX idx_order_operations_order_id ON order_operations(order_id);
    END IF;
    
    IF NOT EXISTS (SELECT 1 FROM information_schema.statistics 
                  WHERE table_schema = 'order_system' 
                  AND table_name = 'order_operations' 
                  AND index_name = 'idx_order_operations_operator_id') THEN
        CREATE INDEX idx_order_operations_operator_id ON order_operations(operator_id);
    END IF;
END //
DELIMITER ;

-- 执行存储过程
CALL create_index_if_not_exists();

-- 删除存储过程
DROP PROCEDURE IF EXISTS create_index_if_not_exists;

-- 检查表结构
SHOW CREATE TABLE users;
SHOW CREATE TABLE orders;
SHOW CREATE TABLE order_operations;

-- 检查索引
SHOW INDEX FROM orders;
SHOW INDEX FROM order_operations;

-- 检查外键约束
SELECT 
    TABLE_NAME,
    COLUMN_NAME,
    CONSTRAINT_NAME,
    REFERENCED_TABLE_NAME,
    REFERENCED_COLUMN_NAME
FROM
    INFORMATION_SCHEMA.KEY_COLUMN_USAGE
WHERE
    REFERENCED_TABLE_SCHEMA = 'order_system'
    AND TABLE_NAME IN ('orders', 'order_operations');

-- 插入测试用户
INSERT INTO users (username, password) VALUES ('test', '$2a$10$91B05rV2XZ9VRENZHdLcq.fOuMuzf0K4OYpkutrWL9FNdj5uWzbvi');

-- 验证用户是否插入成功
SELECT * FROM users;

INSERT INTO orders (user_id, dish_info, status) VALUES (1, '测试菜品', 0);
SELECT * FROM orders;
SHOW CREATE TABLE orders;