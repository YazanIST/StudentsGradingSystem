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
  <%=request.getAttribute("courseName")%> course info:
</p>
<%for (String type: ((LinkedHashMap<String, Integer>) request.getAttribute("courseInfo")).keySet()) {%>
- <%=type%> : <%=((LinkedHashMap<String, Integer>) request.getAttribute("courseInfo")).get(type)%> <br/>
<%}%>
<p>
  <a href="RequestListServlet">Requests Page</a>
</p>
<p>
  <a href="home">Home page (Logout)</a>
</p>
</body>
</html>
