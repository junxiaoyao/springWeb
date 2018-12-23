package com.jxy.springMvc.configs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 10:16
 * @Description:
 */
@Configuration
@EnableTransactionManagement
@Import(DataConfig.class)
@ComponentScan(basePackages = {"com.jxy.springMvc.service","com.jxy.springMvc.dao","com.jxy.springMvc.configs"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class Rootconfig {
}
