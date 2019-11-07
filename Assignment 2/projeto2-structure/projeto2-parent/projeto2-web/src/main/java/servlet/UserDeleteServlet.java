package servlet;

import data.Item;
import ejb.ItemBean;
import ejb.LoginBean;
import ejb.UserBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "UserDeleteServlet",
        urlPatterns = "/UserDeleteServ")
public class UserDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    UserBean ub;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getParameter("opcao").equals("sim")){
            LoginBean bean=(LoginBean) request.getSession(true).getAttribute("currentSessionUser");
            ub.Delete_UserAccount(bean.getEmail());
            request.getSession(true).removeAttribute("currentSessionUser");
            response.sendRedirect("index.jsp");
        }
        else {
            response.sendRedirect("menu.jsp");
        }


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }



}
