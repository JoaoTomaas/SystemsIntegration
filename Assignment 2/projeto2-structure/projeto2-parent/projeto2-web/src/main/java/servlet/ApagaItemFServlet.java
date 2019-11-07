package servlet;

import data.Item;
import ejb.ItemBean;
import ejb.LoginBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "ApagaItemFServlet",
        urlPatterns = "/ApagaItemFServ")
public class ApagaItemFServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("param");

        ib.Apagar_Item(Integer.parseInt(id));

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //response.sendRedirect("fail.jsp");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Item apagado com sucesso');");
        out.println("location='menu.jsp';");
        out.println("</script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
