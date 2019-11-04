<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 03/11/2019
  Time: 02:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.*" %>
<%@ page import ="data.Item" %>
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
    List resultado= (List) request.getAttribute("lista_todos");

    Iterator it = resultado.iterator();
    out.println("<br>Escolha o que quer comprar <br><br>");
    while(it.hasNext()){
        Item i = (Item) it.next();
        out.println(i.getId() + ". " + i.getName() +"<br>");
    }
%>

<input type="text" name="example" list="resultado">
<datalist id="resultado">
    <option value="A">
    <option value="B">
</datalist>

</body>
</html>
