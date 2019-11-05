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
<!DOCTYPE html>
<html>
<head>
    <title>Lista de Items</title>
</head>
<body>

<h1>
    Items Disponiveis no MyBay
</h1>
<%
    if (request.getAttribute("lista_todos") != null) {
        List resultado = (List) request.getAttribute("lista_todos");

        Iterator it = resultado.iterator();
        //out.println("<br>Escolha o que quer comprar <br><br>");
        while (it.hasNext()) {
            Item i = (Item) it.next();
            out.println(i.getId() + ". " + i.getName() + "<br>");
        }
    }else if (request.getAttribute("lista_country") != null) {
        List lista = (List) request.getAttribute("lista_country");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items do seu país<br><br>");
        while (it.hasNext()) {
            Item i = (Item) it.next();
            out.println(i.getId() + ". " + i.getName() + "<br>");
        }
    }
    else if (request.getAttribute("lista_categoria") != null){
        List lista = (List) request.getAttribute("lista_categoria");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items da categoria pretendida<br><br>");
        while (it.hasNext()) {
            Item i = (Item) it.next();
            out.println(i.getId() + ". " + i.getName() + "<br>");
        }
    }
    else if (request.getAttribute("lista_range") != null){
        List lista = (List) request.getAttribute("lista_range");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items dentro do price range<br><br>");
        while (it.hasNext()) {
            Item i = (Item) it.next();
            out.println(i.getId() + ". " + i.getName() + "<br>");
        }
    }
    else if (request.getAttribute("lista_data") != null){
        List lista = (List) request.getAttribute("lista_data");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items publicados após a data indicada<br><br>");
        while (it.hasNext()) {
            Item i = (Item) it.next();
            out.println(i.getId() + ". " + i.getName() + "<br>");
        }
    }
    else if (request.getAttribute("lista_myitems") != null){
        List lista = (List) request.getAttribute("lista_myitems");

        Iterator it = lista.iterator();
        out.println("<br>Estes sao os items que tem à venda<br><br>");
        if (it.hasNext()) {
            while (it.hasNext()) {
                Item i = (Item) it.next();
                out.println(i.getId() + ". " + i.getName() + "<br>");
            }
        }
        else{
            out.println("Não possui items à venda" + "<br>");
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