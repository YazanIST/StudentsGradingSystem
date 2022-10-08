package com.atypon.system.controllers;

import com.atypon.system.CurrentServedStudent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestListController {
    @GetMapping("request-list")
    public String getList(Model model) {
        if (!CurrentServedStudent.isAuthenticated()) {
            return "failed-login";
        }
        return "request-list";
    }
}
