<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 05/11/2019
  Time: 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Procura por price range</title>
</head>
<body>
<form method="post" action="RangeServ" >
    Preço mínimo <input type="text" name="minimum"/><br/><br/>
    Preço máximo <input type="text" name="maximum"/><br/><br/>
    <input type="submit" value="Search"/>
</form>

</body>
</html>
