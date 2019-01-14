package com.jxy.springMvc.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class EnableSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("ADMIN", "USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
       // web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin().loginPage("longin")
                .and()
                // 开始请求权限配置
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/loginIn").permitAll()
                .antMatchers("/login").permitAll();
                //允许所以通过

    }
}
