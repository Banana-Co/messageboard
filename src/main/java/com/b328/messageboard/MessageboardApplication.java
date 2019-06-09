package com.b328.messageboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.b328.messageboard.dao")
public class MessageboardApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageboardApplication.class, args);
    }

}
