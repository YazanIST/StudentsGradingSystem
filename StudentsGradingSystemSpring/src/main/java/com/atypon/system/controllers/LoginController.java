package com.atypon.system.controllers;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.Student;
import com.atypon.system.StudentsDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("student", new Student());
        return "login";
    }

    @PostMapping("login")
    public String postLogin(@ModelAttribute Student student, Model model) throws SQLException {
        CurrentServedStudent.authenticate(student);
        if (CurrentServedStudent.isAuthenticated()) {
            return "request-list";
        }
        return "failed-login";
    }
}
