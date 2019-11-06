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

@WebServlet (name = "EditFinalServlet",
        urlPatterns = "/EditFinalServ")
public class EditFinalServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String category = request.getParameter("categoria");
        String country = request.getParameter("country");
        int price = Integer.parseInt(request.getParameter("price"));

        ib.Editar_Item_Info(id, nome, category, country, price);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //response.sendRedirect("fail.jsp");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Informação do item atualizada com sucesso');");
        out.println("location='menu.jsp';");
        out.println("</script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}