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
        return new SQL() {{
            UPDATE("orders");
            SET("status = #{status}");
            SET("updated_at = NOW()");
            WHERE("id = #{id}");
        }}.toString();
    }
}
