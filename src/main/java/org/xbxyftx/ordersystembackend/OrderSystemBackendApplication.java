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
    public void printDbName() throws Exception {
        try (Connection conn = dataSource.getConnection()) {
            System.out.println("当前数据库: " + conn.getCatalog());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemBackendApplication.class, args);
    }

}
