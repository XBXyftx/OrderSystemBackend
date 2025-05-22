package org.xbxyftx.ordersystembackend.mapper;

import org.apache.ibatis.jdbc.SQL;
import java.util.Map;

public class UserSqlProvider {

    /**
     * 根据用户名和密码动态生成查询用户信息的SQL语句
     *
     * @param params 参数Map，包含 username 和 password
     * @return 动态生成的SQL语句
     */
    public String findByCondition(Map<String, Object> params) {
        // 创建一个SQL对象
        return new SQL() {{
            // 查询所有字段
            SELECT("*");
            // 从users表中选择
            FROM("users");
            // 根据用户名查询
            WHERE("username = #{username}");
            // 如果参数Map中包含password，则根据密码查询
            if (params.containsKey("password")) {
                WHERE("password = #{password}");
            }
        }}.toString();
    }
}