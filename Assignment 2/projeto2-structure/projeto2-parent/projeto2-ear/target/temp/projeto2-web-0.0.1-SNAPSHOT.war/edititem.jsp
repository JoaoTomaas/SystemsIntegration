<%@ page import="java.util.*" %>
<%@ page import="data.Item" %>
<%@ page import="dto.ItemDTO" %><%--
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
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>


<%

    if (request.getAttribute("lista_myitems") != null) {
        List resultado = (List) request.getAttribute("lista_myitems");
        Iterator it = resultado.iterator();
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
%>



<a href="EditItemFillServ?id=<%=i.getId()%>"><%=i.getName()%></a>

<br>
<%
        }
    }
%>

</body>
</html>
