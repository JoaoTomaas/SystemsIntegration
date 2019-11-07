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

@WebServlet (name = "CountryServlet",
        urlPatterns = "/CountryServ")
public class CountryServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = (LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String utilizador = bean.getEmail();
        String name = request.getParameter("nome");
        if (request.getParameter("sort") == null) {
            List<ItemDTO> country_items = ib.Procurar_Items_Country(name, utilizador);
            request.setAttribute("lista_country", country_items);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);

        } else {
            int metodo = Integer.parseInt(request.getParameter("sort"));
            switch (metodo) {
                case 1:
                    List<ItemDTO> country_items = ib.Procurar_Items_Country1(name, utilizador,1);
                    request.setAttribute("lista_country", country_items);
                    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 2:
                    country_items = ib.Procurar_Items_Country2(name, utilizador,1);
                    request.setAttribute("lista_country", country_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 3:
                    country_items = ib.Procurar_Items_Country3(name, utilizador,1);
                    request.setAttribute("lista_country", country_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 4:
                    country_items = ib.Procurar_Items_Country1(name, utilizador,0);
                    request.setAttribute("lista_country", country_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 5:
                    country_items = ib.Procurar_Items_Country2(name, utilizador,0);
                    request.setAttribute("lista_country", country_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 6:
                    country_items = ib.Procurar_Items_Country3(name, utilizador,0);
                    request.setAttribute("lista_country", country_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
