package com.atypon.system.servlets;

import com.atypon.system.CurrentServedStudent;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "home-servlet", value = "/home")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CurrentServedStudent.reset();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + "Home Page" + "</h1>");
        out.println("<a href=\"login\">Login from here</a>");
        out.println("</body></html>");
    }
}
