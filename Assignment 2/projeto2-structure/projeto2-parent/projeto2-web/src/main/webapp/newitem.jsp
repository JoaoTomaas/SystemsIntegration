<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 05/11/2019
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Inserir novo item</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<form method="post" action="NewItemServ" enctype="multipart/form-data">
    Name <input type="text" name="nome"/><br/><br/>
    Category <input type="text" name="categoria"/><br/><br/>
    Country of Origin <input type="text" name="country"/><br/><br/>
    Price <input type="text" name="price"/><br/><br/>
    Imagem <input type="file" name="file"/><br/><br/>
    <input type="submit" value="Insert"/>
</form>
</body>
</html>
