<%--
  Created by IntelliJ IDEA.
  User: duarteguerreiro
  Date: 05/11/2019
  Time: 11:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<form method="post" action="UpdateUserServ" >

    Email:<input type="text" name="email" value=${email}><br/><br/>
    Password:<input type="password" name="password" /><br/><br/>
    Nome :<input type="text" name="nome"value="${nome}" ><br/><br/>
    Pais :<input type="text" name="pais"value="${pais}" ><br/><br/>
    Idade :<input type="number" name="idade"value=${idade}><br/><br/>
    <input type="submit" value="update"/>
</form>
</body>
</html>

