package com.ruizhiqi.controller;

import com.ruizhiqi.entity.User;
import com.ruizhiqi.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    @RequestMapping("/test")
    public String test(){
        List<User> users = userService.showAll();
        for (User user : users) {
            System.out.println(user);
        }
        return "index";
    }
}
