<%@ page import="com.atypon.system.CurrentServedStudent" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests Page</title>
</head>

    <body>
        <p>
            Welcome:)
        </p>
        <p>
            ID:
            <%=CurrentServedStudent.getStudentId()%>
        </p>
        <p>
            <a href="AllCoursesServlet" >
                View all courses
            </a>
        </p>
        <p>
            <a href="AllMarksServlet" >
                View all marks
            </a>
        </p>
        <p>
            <a href="CourseInfoServlet" >
                View a course info
            </a>
        </p>
        <p>
            <a href="home">Home page (Logout)</a>
        </p>
    </body>
</html>
