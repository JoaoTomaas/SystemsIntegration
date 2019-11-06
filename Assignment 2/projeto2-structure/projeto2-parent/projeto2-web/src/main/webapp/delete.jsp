<%@ page import="java.util.*" %>
<%@ page import="data.Item" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apagar Item</title>
</head>
<body>



<%

    if (request.getAttribute("lista_myitems") != null) {
        List resultado = (List) request.getAttribute("lista_myitems");
        Iterator it = resultado.iterator();
        while (it.hasNext()) {
            Item i = (Item) it.next();
            //out.println(i.getId() + ". " + i.getName() + "<br>");
            %>



        <a href="ApagaItemFServ?param=<%=i.getId()%>"><%=i.getName()%></a>

<!--form>
    <input type="hidden" name="insert" value="insert">
</form-->

<br>
        <%
        }
    }

%>


</body>
</html>
