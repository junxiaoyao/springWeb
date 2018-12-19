package com.jxy.springMvc.controller;

import com.jxy.springMvc.entity.Users;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
}
