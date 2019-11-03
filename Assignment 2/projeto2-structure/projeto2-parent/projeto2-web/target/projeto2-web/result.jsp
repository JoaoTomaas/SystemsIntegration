<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 03/11/2019
  Time: 02:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Items</title>
</head>
<body>

<h1>
    Items Disponiveis no MyBay
</h1>
<%
    List resultado= (List) request.getAttribute("lista_items");
    Iterator it = resultado.iterator();
    out.println("<br>Escolha o que quer comprar <br><br>");
    while(it.hasNext()){
        out.println(it.next()+"<br>");
    }
%>

</body>
</html>
