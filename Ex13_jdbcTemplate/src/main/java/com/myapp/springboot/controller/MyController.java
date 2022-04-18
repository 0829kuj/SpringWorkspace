package com.myapp.springboot.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myapp.springboot.jdbc.MyUserDao;

@Controller
public class MyController {

//    @Autowired
//    private MyUserDao userDao;

//    @RequestMapping("/")
    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    // 모든 유저를 불러와 화면에 출력
    //@RequestMapping(value = "/user", method = RequestMethod.GET)
//    @GetMapping("/user")
//    public String userlistPage(Model model) {
//        model.addAttribute("users", userDao.list());
//        return "userlist";
//    }

}
