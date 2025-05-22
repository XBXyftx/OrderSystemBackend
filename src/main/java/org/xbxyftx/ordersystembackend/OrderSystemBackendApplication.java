package org.xbxyftx.ordersystembackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
@MapperScan(basePackages = {"org.xbxyftx.ordersystembackend.mapper"})
public class OrderSystemBackendApplication {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    // 在Bean初始化后执行该方法
    public void printDbName() throws Exception {
        // 获取数据库连接
        try (Connection conn = dataSource.getConnection()) {
            // 打印当前数据库
            System.out.println("当前数据库: " + conn.getCatalog());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemBackendApplication.class, args);
    }

}
