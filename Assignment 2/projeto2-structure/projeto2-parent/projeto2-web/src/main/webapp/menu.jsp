<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 03/11/2019
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu Inicial</title>
</head>

<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="index.html">Logout</a></div>

<div style="margin-left: 2%">
<h1>Menu Inicial</h1>
<!-- USER-->
<h2>Account Operations</h2>
<h3>Edit personal information</h3>
<h3>Delete account</h3>
<br>

<!-- ITEM -->
<h2>Item Operations</h2>
    <a href="${pageContext.request.contextPath}/MyItemsServ">List all items that I have for sale</a><br />
    <a href="${pageContext.request.contextPath}/NewItemServ">Put a new item for sale</a><br />
<h3>Edit item information</h3>
<h3>Delete an item</h3>
<br>

<!-- SEARCH -->
<h2>Search Operations</h2>
    <a href="${pageContext.request.contextPath}/ItemServ">Search for all items</a><br />
    <a href="${pageContext.request.contextPath}/category.jsp">Search inside a certain category</a><br />
    <a href="${pageContext.request.contextPath}/pricerange.jsp">Search within a price range</a><br/>
    <a href="${pageContext.request.contextPath}/CountryServ">Search in my country</a><br />
    <a href="${pageContext.request.contextPath}/date.jsp">Search items published after a given date</a>
</div>

</body>
<!--a id="search_category" onclick="location.href='@Url.Action(ItemServ)'">SSearch inside a certain category</a><br /> -->
</html>