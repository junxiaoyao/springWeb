package com.jxy.springMvc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/userInfo")
    public void testLogin(HttpServletRequest request){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getPrincipal();
    }
}
