package org.unicat.servicefitness;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "org.unicat")
@MapperScan(basePackages = "org.unicat.**.mapper")
public class FitnessServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(FitnessServiceApplication.class, args);
    }
}
