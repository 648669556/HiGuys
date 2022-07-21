package com.guy.web.config;

import com.guy.common.util.SnowFlake;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjunhong
 * @createAt 2022-07-14  14:50
 */
@Configuration
public class SnowFlakeConfig {

    @Bean
    public SnowFlake productSnowFlake(){
        //先默认实现
        return new SnowFlake(1,1);
    }


}
