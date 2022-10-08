package com.atypon.system.controllers.view;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.StudentsDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.sql.SQLException;

@Controller
public class AllCoursesViewController {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

    @GetMapping("/all-courses")
    public String getAllCourses(Model model) throws SQLException {
        if (!CurrentServedStudent.isAuthenticated()) {
            return "failed-login";
        }
        model.addAttribute("courses", studentsDatabaseService.getAllCourses());
        return "all-courses";
    }
}
