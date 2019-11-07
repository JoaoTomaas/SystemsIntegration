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

@WebServlet (name = "PriceRangeServlet",
        urlPatterns = "/RangeServ")
public class PriceRangeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String min_bound = request.getParameter("minimum");
        String max_bound = request.getParameter("maximum");
        String name = request.getParameter("nome");
        if(request.getParameter("sort")==null) {
            List <ItemDTO> lista = ib.Procurar_Items_PriceRange(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound));
            request.setAttribute("lista_range", lista);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);

        }
        else{

            int metodo=Integer.parseInt(request.getParameter("sort"));
            switch (metodo){
                case 1:
                    List <ItemDTO> lista = ib.Procurar_Items_PriceRange1(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),1);
                    request.setAttribute("lista_range", lista);
                    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 2:
                    lista = ib.Procurar_Items_PriceRange2(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),1);
                    request.setAttribute("lista_range", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 3:
                    lista = ib.Procurar_Items_PriceRange3(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),1);
                    request.setAttribute("lista_range", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 4:
                    lista = ib.Procurar_Items_PriceRange1(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),0);
                    request.setAttribute("lista_range", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 5:
                    lista = ib.Procurar_Items_PriceRange2(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),0);
                    request.setAttribute("lista_range", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 6:
                    lista = ib.Procurar_Items_PriceRange3(name,Float.parseFloat(min_bound), Float.parseFloat(max_bound),0);
                    request.setAttribute("lista_range", lista);
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