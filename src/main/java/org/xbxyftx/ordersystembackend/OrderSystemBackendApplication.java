package org.xbxyftx.ordersystembackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"org.xbxyftx.ordersystembackend.mapper"})
public class OrderSystemBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSystemBackendApplication.class, args);
    }

}
