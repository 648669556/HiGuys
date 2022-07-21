package com.guy.web.init;

import com.zhiyi.generalbeanplus.GeneralBeanService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;

/**
 * @author chenjunhong
 * @createAt 2022-07-15  09:50
 */
@Configuration
@Slf4j
public class TestInit implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
