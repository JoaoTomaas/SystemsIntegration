<%@ page import="java.util.*" %>
<%@ page import="data.Item" %><%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 05/11/2019
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar item</title>
</head>
<body>
<%

    if (request.getAttribute("lista_myitems") != null) {
        List resultado = (List) request.getAttribute("lista_myitems");
        Iterator it = resultado.iterator();
        while (it.hasNext()) {
            Item i = (Item) it.next();
%>
<a href="edittitem.jsp?id=<%=i.getId()%>"><%=i.getName()%></a>

<br>
<%
        }
    }
%>

</body>
</html>
