package com.ip.hw8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class Hw8Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw8Application.class, args);
    }

}
