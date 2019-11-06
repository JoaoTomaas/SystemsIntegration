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

@WebServlet (name = "DeleteItemServ",
        urlPatterns = "/DeleteItemServ")
public class DeleteItemServ extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = (LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String utilizador = bean.getEmail();

        List<Item> my_items = ib.Listar_Items_aVenda(utilizador);
        request.setAttribute("lista_myitems_edit", my_items);

        RequestDispatcher view = request.getRequestDispatcher("edititemjsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}