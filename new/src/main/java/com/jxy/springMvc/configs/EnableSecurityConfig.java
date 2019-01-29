package com.jxy.springMvc.configs;

import com.jxy.springMvc.security.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@ComponentScan("com.jxy.springMvc.security")
public class EnableSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailService userDetailService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailService);
      /*  auth.inMemoryAuthentication()
                .withUser("user").password("123456").roles("USER").and()
                .withUser("admin").password("password").roles("ADMIN", "USER");*/
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
       // web.ignoring().antMatchers("/**");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity .formLogin()
                //　//这里程序登陆页面，允许所有人进行登陆
                .loginPage("/login").permitAll()
                .loginProcessingUrl("/login")
                //失败重新登录
                .failureForwardUrl("/login")
                //成功进入主界面
               // .successForwardUrl("/")
                .and().authorizeRequests().antMatchers("/").hasRole("ADMIN");
        /*httpSecurity
                .formLogin().loginPage("longin")
                .and()
                // 开始请求权限配置
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/loginIn").permitAll()
                .antMatchers("/login").permitAll();*/
                //允许所以通过

    }
}
