package servlet;

import data.Item;
import dto.ItemDTO;
import ejb.ItemBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "ItemServlet",
        urlPatterns = "/ItemServ")
public class ItemServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("sort")==null) {
            List<ItemDTO> all_items = ib.Procurar_Items(request.getParameter("nome"));
            request.setAttribute("lista_todos", all_items);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);

        }
        else{
            //System.out.println("testeeeeeeee "+request.getParameter("nome_aux"));
            int metodo=Integer.parseInt(request.getParameter("sort"));
            switch (metodo){
                case 1:
                    List<ItemDTO> all_items = ib.Procurar_Items1(request.getParameter("nome"),1);
                    request.setAttribute("lista_todos", all_items);
                    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 2:
                    all_items = ib.Procurar_Items2(request.getParameter("nome"),1);
                    request.setAttribute("lista_todos", all_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 3:
                    all_items = ib.Procurar_Items3(request.getParameter("nome"),1);
                    request.setAttribute("lista_todos", all_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 4:
                    all_items = ib.Procurar_Items1(request.getParameter("nome"),0);
                    request.setAttribute("lista_todos", all_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 5:
                    all_items = ib.Procurar_Items2(request.getParameter("nome"),0);
                    request.setAttribute("lista_todos", all_items);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 6:
                    all_items = ib.Procurar_Items3(request.getParameter("nome"),0);
                    request.setAttribute("lista_todos", all_items);
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
