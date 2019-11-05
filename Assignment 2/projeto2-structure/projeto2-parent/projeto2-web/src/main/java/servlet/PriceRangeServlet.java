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
import java.util.List;

@WebServlet (name = "PriceRangeServlet",
        urlPatterns = "/RangeServ")
public class PriceRangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String min_bound = request.getParameter("minimum");
        String max_bound = request.getParameter("maximum");

        List <Item> lista = ib.Procurar_Items_PriceRange(Integer.parseInt(min_bound), Integer.parseInt(max_bound));

        request.setAttribute("lista_range", lista);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }


}