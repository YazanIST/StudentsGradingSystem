package com.atypon.system.servlets;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.Student;
import com.atypon.system.StudentsDatabaseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;

        if (CurrentServedStudent.isAuthenticated()) {
            requestDispatcher = request.getRequestDispatcher("/view.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        Student newStudent = new Student(
                Integer.parseInt(request.getParameter("id")),
                request.getParameter("password")
        );

        try {
            CurrentServedStudent.authenticate(newStudent);
            if (CurrentServedStudent.isAuthenticated()) {
                request.setAttribute("student", newStudent);
                requestDispatcher = request.getRequestDispatcher("/view.jsp");
            } else {
                requestDispatcher = request.getRequestDispatcher("/failed-login.html");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        requestDispatcher.forward(request, response);
    }
}
