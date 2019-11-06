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

@WebServlet (name = "NewItemServlet",
        urlPatterns = "/NewItemServ")
public class NewItemServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = (LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String user_mail = bean.getEmail();
        String nome = request.getParameter("nome");
        String category = request.getParameter("categoria");
        String country = request.getParameter("country");
        String price = request.getParameter("price");

        ib.Inserir_Novo_Item(nome, category, country, Integer.parseInt(price), user_mail);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Novo item inserido com sucesso');");
        out.println("location='menu.jsp';");
        out.println("</script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
