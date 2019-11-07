<%--
  Created by IntelliJ IDEA.
  User: duarteguerreiro
  Date: 04/11/2019
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apagar conta</title>

</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<form method="post" action="UserDeleteServ" >
    Tem a certeza que quer apagar a conta? <br>
    <input type="radio" name="opcao" value="sim" checked> Sim<br>
    <input type="radio" name="opcao" value="nao"> Nao<br>
    <input type="submit" value="continuar">
</form>
</body>
</html>

