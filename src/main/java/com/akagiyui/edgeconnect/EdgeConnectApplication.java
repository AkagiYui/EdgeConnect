package com.akagiyui.edgeconnect;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author AkagiYui
 */
@SpringBootApplication
@MapperScan("com.akagiyui.edgeconnect.mapper")
public class EdgeConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdgeConnectApplication.class, args);
    }

}
