package servlet;


import dto.ItemDTO;
import dto.UtilizadorDTO;
import ejb.ItemBean;
import ejb.LoginBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "EditItemFillServlet",
        urlPatterns = "/EditItemFillServ")
public class EditItemFillServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ItemDTO item = ib.get_item_dto(id);

        request.setAttribute("id", item.getId());
        request.setAttribute("nome", item.getName());
        System.out.println("QUE CATEGORIA ->>>>>>>>>>>>>> " + item.getCategory());
        request.setAttribute("categoria", item.getCategory());
        request.setAttribute("pais", item.getCountry_of_origin());
        request.setAttribute("preco", item.getPrice());
        request.getRequestDispatcher("edititemfill.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



}