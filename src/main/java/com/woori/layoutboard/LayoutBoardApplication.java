package com.woori.layoutboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LayoutBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(LayoutBoardApplication.class, args);
    }

}
