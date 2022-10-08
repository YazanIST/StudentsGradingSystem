<%@ page import="com.atypon.system.StudentsDatabaseService" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>All marks</title>
</head>
<body>
<p>
  All courses:
</p>
<%for (String course: ((LinkedList<String>) request.getAttribute("courses"))) {%>
- <%=course%> <br/>
<%}%>
<p>
  <a href="RequestListServlet">Requests Page</a>
</p>
<p>
  <a href="home">Home page (Logout)</a>
</p>
</body>
</html>
