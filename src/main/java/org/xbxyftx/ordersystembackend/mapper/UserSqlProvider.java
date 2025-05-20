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
        return new SQL() {{
            SELECT("*");
            FROM("users");
            WHERE("username = #{username}");
            if (params.containsKey("password")) {
                AND("password = #{password}");
            }
        }}.toString();
    }
}