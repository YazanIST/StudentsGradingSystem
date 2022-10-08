package com.atypon.system.servlets.view;

import com.atypon.system.StudentsDatabaseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AllCoursesServlet", value = "/AllCoursesServlet")
public class AllCoursesServlet extends HttpServlet {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        try {
            request.setAttribute("courses", studentsDatabaseService.getAllCourses());
            requestDispatcher = request.getRequestDispatcher("/all-courses.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
