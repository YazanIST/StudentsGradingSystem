package com.atypon.system.controllers.view;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.StudentsDatabaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class CourseInfoViewController {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

    @GetMapping("/course-info")
    public String getCourseInfo(Model model) throws SQLException {
        if (!CurrentServedStudent.isAuthenticated()) {
            return "failed-login";
        }
        model.addAttribute("allCourses", studentsDatabaseService.getAllCourses());
        model.addAttribute("course", new Course());
        return "get-course-info";
    }

    @PostMapping("/course-info")
    public String postCourseInfo(@ModelAttribute Course course, Model model) throws SQLException {
        if (!studentsDatabaseService.checkCourseForId(course.getName(), CurrentServedStudent.getStudentId())) {
            return "failed-pick";
        }
        model.addAttribute("courseInfo", studentsDatabaseService.getCourseInfo(course.getName()));
        return "view-course-info";
    }
}

class Course {
    private String name;

    public Course() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
