package com.guy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author chenjunhong
 *  2022-07-14  11:50
 */
@SpringBootApplication
@EnableTransactionManagement
@EnableAsync
@MapperScan("com.service")
public class HiGuysApplication {

    public static void main(String[] args) {
        SpringApplication.run(HiGuysApplication.class,args);
    }
}
