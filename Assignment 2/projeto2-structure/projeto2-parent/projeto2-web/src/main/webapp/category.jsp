<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 05/11/2019
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Procura por categoria</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

    <form method="post" action="CategoryServ" >
        Categoria <input type="text" name="categoria"/><br/><br/>
        <input type="submit" value="Search"/>
    </form>

</body>
</html>
