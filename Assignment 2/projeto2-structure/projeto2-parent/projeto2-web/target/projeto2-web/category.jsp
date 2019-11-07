<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<form action="CategoryServ" method="post">

    Nome :<input type="text" name="nome"/><br/><br/>

    <select name="category">
        <c:forEach items="${listCategory}" var="category">
            <option value="${category}">${category}</option>
        </c:forEach>
    </select>
    <br><br>
    <input type="submit">
</form>

</body>
</html>
