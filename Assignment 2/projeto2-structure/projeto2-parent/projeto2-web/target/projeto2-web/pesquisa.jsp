<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pesquisa</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<form method="get" action="ItemServ" >
    Nome do item <input type="text" name="nome"/><br/><br/>
    <input type="submit" value="Procurar"/>
</form>
</body>
</html>
