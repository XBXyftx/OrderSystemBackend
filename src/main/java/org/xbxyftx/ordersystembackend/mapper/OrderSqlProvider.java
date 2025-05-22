package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.jdbc.SQL;
import org.xbxyftx.ordersystembackend.entity.Order;

public class OrderSqlProvider {
    
    /**
     * 动态生成更新订单状态的SQL语句
     *
     * @param order 订单对象
     * @return 动态生成的SQL语句
     */
    public String updateStatus(Order order) {
        // 创建一个SQL对象
        return new SQL() {{
            // 更新orders表
            UPDATE("orders");
            // 设置status字段为order对象的status属性
            SET("status = #{status}");
            // 设置updated_at字段为当前时间
            SET("updated_at = NOW()");
            // 设置WHERE条件为id字段等于order对象的id属性
            WHERE("id = #{id}");
        }}.toString();
    }
}
