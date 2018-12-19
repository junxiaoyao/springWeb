package springMvc.configs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 10:16
 * @Description:
 */
@Configuration
@ComponentScan(basePackages = {"springMvc.service"},
        excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION,value = EnableWebMvc.class)})
public class Rootconfig {
}
