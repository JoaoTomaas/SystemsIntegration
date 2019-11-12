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
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<form method="post" action="EditFinalServ" enctype="multipart/form-data">
    <%out.println("<br>Detalhes do Item <br><br>");%>
    <input type="hidden" name="id" value=${id} ><br/><br/>
    Name <input type="text" name="nome" value="${nome}" ><br/><br/>
    Category <input type="text" name="categoria" value="${categoria}" ><br/><br/>
    Country of Origin <input type="text" name="country" value="${pais}" ><br/><br/>
    Price <input type="text" name="price" value=${preco} ><br/><br/>
    Image <input type="file" name ="file" ><br/><br/>
    <input type="submit" value="Edit"/>
</form>
</body>
</html>
