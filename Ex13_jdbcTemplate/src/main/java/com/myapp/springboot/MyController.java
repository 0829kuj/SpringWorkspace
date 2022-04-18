package com.myapp.springboot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.springboot.jdbc.MyUserDao;

@Controller
public class MyController {

    @Autowired
    private MyUserDao userDao;

    @RequestMapping("/")
    public String root() {
        return "home";
    }

    //@RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    public String userlistPage(Model model) {
        model.addAttribute("users", userDao.list());
        return "userlist";
    }

}
