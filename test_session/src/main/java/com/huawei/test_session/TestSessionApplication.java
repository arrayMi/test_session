package com.huawei.test_session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class TestSessionApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSessionApplication.class, args);
    }
}
