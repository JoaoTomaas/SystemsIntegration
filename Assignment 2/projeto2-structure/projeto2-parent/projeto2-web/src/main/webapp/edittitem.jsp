<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 06/11/2019
  Time: 00:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Item</title>
</head>
<body>
<form method="post" action="EditFinalServ" >
    Id <input type="text" name="id" value="<%request.getParameter("id");%>" /><br/><br/>
    Name <input type="text" name="nome"/><br/><br/>
    Category <input type="text" name="categoria"/><br/><br/>
    Country of Origin <input type="text" name="country"/><br/><br/>
    Price <input type="text" name="price"/><br/><br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
