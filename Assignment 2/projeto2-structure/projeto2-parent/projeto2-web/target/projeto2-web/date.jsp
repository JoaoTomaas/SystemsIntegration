<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 05/11/2019
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Procura por data</title>
</head>
<body>
<div style="margin-left: 2%; margin-top: 2%; text-align: right; float: left">
<form method="post" action="DateServ" >
    Dia <input type="text" name="dia"/> <br/><br/>
    Mês <input type="text" name="mes"/><br/><br/>
    Ano <input type="text" name="ano"/><br/><br/>
    <input type="submit" value="Search"/>
</form>
</div>
</body>
</html>
