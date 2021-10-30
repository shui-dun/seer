package com.sd.seerserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class SeerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SeerServerApplication.class, args);
    }

}

