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
      All marks:
    </p>
    <%for (String course: ((LinkedHashMap<String, Integer>) request.getAttribute("marks")).keySet()) {%>
      - <%=course%> : <%=((LinkedHashMap<String, Integer>) request.getAttribute("marks")).get(course)%> <br/>
    <%}%>
    <p>
      <a href="RequestListServlet">Requests Page</a>
    </p>
    <p>
      <a href="home">Home page (Logout)</a>
    </p>
  </body>
</html>
