package com.atypon.system.servlets.view;

import com.atypon.system.CurrentServedStudent;
import com.atypon.system.StudentsDatabaseService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AllMarksServlet", value = "/AllMarksServlet")
public class AllMarksServlet extends HttpServlet {
    StudentsDatabaseService studentsDatabaseService = new StudentsDatabaseService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = null;
        try {
            request.setAttribute(
                    "marks",
                    studentsDatabaseService.getAllMarks(CurrentServedStudent.getStudentId())
            );
            requestDispatcher = request.getRequestDispatcher("/all-marks.jsp");
            requestDispatcher.forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
