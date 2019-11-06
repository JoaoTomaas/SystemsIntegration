package servlet;

import data.Item;
import data.Utilizador;
import ejb.ItemBean;
import ejb.UserBean;
import jdk.nashorn.internal.ir.RuntimeNode;

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

@WebServlet( name = "ServletTestWeb",
      urlPatterns=  "/MostraItems")
public class ServletTestWeb extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean itemb;
    UserBean ub;
    public ServletTestWeb() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (request.getParameter("fill") != null) {
            //itemb.insert_item_test();
            //ub.Insere_User(22, "joao.miguel.tomas@hotmail.com","teste1", "Portugal", "João Tomás");
            //ub.test_user();
            out.println("<h1>Populate: OK!</h1>");
        }else{
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*doGet(request, response);
        List<Item> lista = itemb.Procurar_Items();

        request.setAttribute("lista_items", lista);
        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);*/
    }

}
