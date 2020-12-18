package com.lovezc.forever;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.mybatis.spring.annotation.MapperScan;
/**
 * @author 史浩健
 * @date 2020年4月8日
 */
@Api(value = "项目启动类")
@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages = "com.lovezc.forever.dao")
@ComponentScan(basePackages = {"com.lovezc.forever.*"})
@EnableWebSocketMessageBroker
public class ForeverApplication {

    @ApiOperation(value = "项目启动方法")
    public static void main(String[] args) {
        SpringApplication.run(ForeverApplication.class, args);
    }

}
