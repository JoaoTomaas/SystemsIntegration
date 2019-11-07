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

@WebServlet (name = "CategoryServlet",
        urlPatterns = "/CategoryServ")
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categ = request.getParameter("category");
        if(request.getParameter("sort")==null) {
            List <ItemDTO> lista = ib.Procurar_Items_Categoria(categ,request.getParameter("nome"));
            request.setAttribute("lista_categoria", lista);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);

        }
        else {
            int metodo=Integer.parseInt(request.getParameter("sort"));
            switch (metodo) {
                case 1:

                    List <ItemDTO> lista = ib.Procurar_Items_Categoria1(categ,request.getParameter("nome"),1);
                    request.setAttribute("lista_categoria", lista);
                    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 2:
                    lista = ib.Procurar_Items_Categoria2(categ,request.getParameter("nome"),1);
                    request.setAttribute("lista_categoria", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 3:
                    lista = ib.Procurar_Items_Categoria3(categ,request.getParameter("nome"),1);
                    request.setAttribute("lista_categoria", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 4:
                    lista = ib.Procurar_Items_Categoria1(categ,request.getParameter("nome"),0);
                    request.setAttribute("lista_categoria", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 5:
                    lista = ib.Procurar_Items_Categoria2(categ,request.getParameter("nome"),0);
                    request.setAttribute("lista_categoria", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 6:
                    lista = ib.Procurar_Items_Categoria3(categ,request.getParameter("nome"),0);
                    request.setAttribute("lista_categoria", lista);
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