package com.jxy.springMvc.controller;

import com.jxy.springMvc.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: ybl
 * @Date: 2018/12/14 0014 11:07
 * @Description:
 */
@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String home(Model model){
        Users users=new Users();
        users.setSex("男的");
        users.setUserName("宝天帝君");
        model.addAttribute("name","luanfa");
        model.addAttribute("user",users);
        return "homePage";
    }
    /*@ExceptionHandler()
    public String notFoundHtml(HttpServletRequest request){
        return "404";
    }*/
}
