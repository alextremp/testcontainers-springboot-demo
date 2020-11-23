package com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.configuration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.github.alextremp.testcontainersdemo.infrastructure.messagestoredb.repository.mybatis")
public class MyBatisConfiguration {
}
