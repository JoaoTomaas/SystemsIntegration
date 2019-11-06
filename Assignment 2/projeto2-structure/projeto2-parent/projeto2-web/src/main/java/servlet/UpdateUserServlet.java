package servlet;


import ejb.LoginBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateUserServlet",
        urlPatterns = "/UpdateUserServ")
public class UpdateUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    UserBean ub;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean=(LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String email_original=bean.getEmail();
        String email_novo = request.getParameter("email");
        String pass = request.getParameter("password");
        String nome = request.getParameter("nome");
        String pais = request.getParameter("pais");
        int age = Integer.parseInt(request.getParameter("idade"));
        ub.Editar_User_Info(email_original,nome,age,pais,pass,email_novo);
        bean.setEmail(email_novo);
        bean.setPassword(pass);
        request.getSession().setAttribute("currentSessionUser",bean);
        response.sendRedirect("menu.jsp");



    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



}
