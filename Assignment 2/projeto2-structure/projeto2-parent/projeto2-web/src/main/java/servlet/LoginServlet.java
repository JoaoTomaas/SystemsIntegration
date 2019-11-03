package servlet;

import ejb.ItemBean;
import ejb.LoginBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet (urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    LoginBean logb;

    public LoginServlet() {super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
