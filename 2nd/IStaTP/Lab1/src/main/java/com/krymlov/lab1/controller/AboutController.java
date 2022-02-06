package com.krymlov.lab1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AboutController {

    @RequestMapping("/")
    public String getPage(){
        return "redirect:/category";
    }

    @RequestMapping("/about")
    public String getAboutPage(){
        return "/about/about";
    }

}
