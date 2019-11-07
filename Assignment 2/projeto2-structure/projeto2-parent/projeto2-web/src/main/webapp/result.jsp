<%--
  Created by IntelliJ IDEA.
  User: joaom
  Date: 03/11/2019
  Time: 02:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import ="java.util.*" %>
<%@ page import ="data.Item" %>
<%@ page import="ejb.LoginBean" %>
<%@ page import="dto.ItemDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Items</title>
</head>
<body>

<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<h1>
    Items Disponíveis no MyBay
</h1>
<%
    if (request.getAttribute("lista_todos") != null) {
        List resultado = (List) request.getAttribute("lista_todos");

        Iterator it = resultado.iterator();
        //out.println("<br>Escolha o que quer comprar <br><br>");
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
            %>
                    <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                    <br>
            <%
    }
    }else if (request.getAttribute("lista_country") != null) {
        List lista = (List) request.getAttribute("lista_country");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items do seu país<br><br>");
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
            //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>
                <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                <br>
            <%
        }
    }
    else if (request.getAttribute("lista_categoria") != null){
        List lista = (List) request.getAttribute("lista_categoria");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items da categoria pretendida<br><br>");
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
            //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>
                <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                <br>
            <%
        }
    }
    else if (request.getAttribute("lista_range") != null){
        List lista = (List) request.getAttribute("lista_range");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items dentro do price range<br><br>");
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
            //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>
                <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                <br>
            <%
        }
    }
    else if (request.getAttribute("lista_data") != null){
        List lista = (List) request.getAttribute("lista_data");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items publicados após a data indicada<br><br>");
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
            //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>
                <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                <br>
            <%
        }
    }
    else if (request.getAttribute("lista_myitems") != null){
        List lista = (List) request.getAttribute("lista_myitems");

        Iterator it = lista.iterator();
        if (it.hasNext()) {
            out.println("<br>Estes são os items que tem à venda: <br><br>");
            while (it.hasNext()) {
                ItemDTO i = (ItemDTO) it.next();
                //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>
                <a href="DetalhesServ?param=<%=i.getId()%>"><%=i.getName()%></a>
                <br>
            <%
            }
        }
        else{
            out.println("Não possui items à venda" + "<br>");
        }
    }
    else if (request.getAttribute("item_details") != null){
        List lista = (List) request.getAttribute("item_details");
        Iterator it = lista.iterator();
        if (it.hasNext()) {

            out.println("<br><h3>Detalhes do item</h3><br>");

            ItemDTO i = (ItemDTO) it.next();
            //Item it = (Item) request.getAttribute("items_details");
            out.print("<b>Id:</b> " + i.getId() + "<br>");
            out.print("<b>Name:</b> " + i.getName() + "<br>");
            out.print("<b>Category:</b> " + i.getCategory() + "<br>");
            out.print("<b>Country of Origin:</b> " + i.getCountry_of_origin() + "<br>");
            out.print("<b>Price:</b> " + i.getPrice() + "<br>");
            out.print("<b>Data de Publicacao:</b> " + i.getPublished_date() + "<br>");
            out.print("<b>Utilizador:</b> " + i.getUtilizador().getName() + "<br>");
        }
    }

%>

<!--input type="text" name="example" list="resultado">
<datalist id="resultado">
    <option value="A">
    <option value="B">
</datalist> -->

</body>
</html>
