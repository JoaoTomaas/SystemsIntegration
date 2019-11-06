package servlet;


import dto.UtilizadorDTO;
import ejb.LoginBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisplayUserServlet",
        urlPatterns = "/DisplayUserServ")
public class DisplayUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    UserBean ub;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean=(LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String email=bean.getEmail();
        UtilizadorDTO user=ub.get_user_teste(email);
        request.setAttribute("email", user.getEmail());
        request.setAttribute("nome", user.getName());
        request.setAttribute("pais", user.getCountry());
        request.setAttribute("idade", user.getAge());
        request.getRequestDispatcher("updateU.jsp").forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



}