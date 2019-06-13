package com.b328.messageboard;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.SpringServletContainerInitializer;

@SpringBootApplication
@MapperScan("com.b328.messageboard.mapper")
public class MessageboardApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MessageboardApplication.class, args);
    }

}
