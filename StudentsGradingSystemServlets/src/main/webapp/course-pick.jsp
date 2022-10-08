<%@ page import="java.util.LinkedList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Course Pick</title>
</head>
<body style="font-size: large; text-align: left">
<p>
  Available courses:
</p>
<p>
  <% for (String s : (LinkedList<String>) request.getAttribute("courses")) { %>
  - <%=s%> <br/>
  <%}%>
</p>
<p>
  Pick a course
</p>
<form method="post" action="CourseInfoServlet" autocomplete="off">
  <p>
    Course:
    <input type="text" name="course">
  </p>
  <p>
    <input type="submit" value="Pick"/>
  </p>
</form>
<a href="RequestListServlet">Requests Page</a>
<a href="home">Home Page (Logout)</a>
</body>
</html>