<%--
  Created by IntelliJ IDEA.
  User: duarteguerreiro
  Date: 06/11/2019
  Time: 23:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pesquisa</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<h3>Items do seu país</h3>
<form method="get" action="CountryServ" >
    Nome do item <input type="text" name="nome"/><br/><br/>
    <input type="submit" value="Procurar"/>
</form>

</body>
</html>
