package servlet;


import data.Item;
import dto.ItemDTO;
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
import java.util.List;

@WebServlet (name = "MyItemsServlet",
        urlPatterns = "/MyItemsServ")
public class MyItemsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = (LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String user_mail = bean.getEmail();
        List<ItemDTO> all_items = ib.Listar_Items_aVenda(user_mail);

        request.setAttribute("lista_myitems", all_items);

        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}