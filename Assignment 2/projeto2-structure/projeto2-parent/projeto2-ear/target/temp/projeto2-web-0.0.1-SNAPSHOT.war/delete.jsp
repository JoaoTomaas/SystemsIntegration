<%@ page import="java.util.*" %>
<%@ page import="data.Item" %>
<%@ page import="dto.ItemDTO" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apagar Item</title>
</head>
<body>
<div style="position: absolute; top:2%; right: 2%;" ><a href="${pageContext.request.contextPath}/LogoutServ">Logout</a></div>

<%
    out.println("<br>Escolha o item que pretende eliminar: <br><br>");
    if (request.getAttribute("lista_myitems_edit") != null) {
        List resultado = (List) request.getAttribute("lista_myitems_edit");
        Iterator it = resultado.iterator();
        while (it.hasNext()) {
            ItemDTO i = (ItemDTO) it.next();
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
