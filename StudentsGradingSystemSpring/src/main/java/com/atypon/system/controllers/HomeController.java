package com.atypon.system.controllers;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.StudentsDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String getHome() {
        CurrentServedStudent.reset();
        return "home";
    }

    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home";
    }
}
