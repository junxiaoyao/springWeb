package com.jxy.springMvc.controller;

import com.jxy.springMvc.entity.UserDetail;
import com.jxy.springMvc.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
    public String home(HttpServletRequest request,Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user=(User)authentication.getPrincipal();
        Users users = new Users();
        users.setSex("男的");
        users.setUserName(user.getUsername());
        model.addAttribute("name", "luanfa");
        model.addAttribute("user", users);
        return "homePage";
    }

    @RequestMapping("login")
    public String loginGet(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String loginPost(UserDetail userDetail, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "login";
    }
    /*@ExceptionHandler()
    public String notFoundHtml(HttpServletRequest request){
        return "404";
    }*/
}
