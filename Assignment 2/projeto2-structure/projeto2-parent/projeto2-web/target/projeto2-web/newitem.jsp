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
<form method="post" action="NewItemServ" >
    Name <input type="text" name="nome"/><br/><br/>
    Category <input type="text" name="categoria"/><br/><br/>
    Country of Origin <input type="text" name="country"/><br/><br/>
    Price <input type="text" name="price"/><br/><br/>
    <input type="submit" value="Insert"/>
</form>
</body>
</html>
