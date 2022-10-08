package com.atypon.system.servlets.view;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.StudentsDatabaseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CourseInfoServlet", value = "/CourseInfoServlet")
public class CourseInfoServlet extends HttpServlet {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        try {
            request.setAttribute("courses", studentsDatabaseService.getAllCourses());
            requestDispatcher = request.getRequestDispatcher("/course-pick.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        try {
            String pickedCourse = request.getParameter("course");
            if (studentsDatabaseService.checkCourseForId(pickedCourse, CurrentServedStudent.getStudentId())) {
                request.setAttribute("courseInfo", studentsDatabaseService.getCourseInfo(pickedCourse));
                request.setAttribute("courseName", pickedCourse);
                requestDispatcher = request.getRequestDispatcher("/course-info.jsp");
            } else {
                requestDispatcher = request.getRequestDispatcher("/failed-pick.html");
            }
        } catch (Exception e) {
            throw new IllegalAccessError();
        }
        requestDispatcher.forward(request, response);
    }
}
