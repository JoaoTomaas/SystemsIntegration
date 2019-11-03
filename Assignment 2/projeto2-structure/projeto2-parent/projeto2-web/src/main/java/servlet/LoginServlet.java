package servlet;

import ejb.ItemBean;
import ejb.LoginBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    LoginBean logb;

    public LoginServlet() {super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1 - Sucesso | 2 - Password errada | 3 - Utilizador nao registado
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        int status  = logb.loginUser(email, pass);

        switch (status) {
            case 1:
                    LoginBean loggedin = new LoginBean();
                    loggedin.setEmail(email);
                    loggedin.setPassword(pass);

                    HttpSession sessao = request.getSession(true);
                    sessao.setAttribute("currentSessionUser", loggedin);
                    response.sendRedirect("menu.jsp");
                break;
            case 2:
                    response.sendRedirect("fail.jsp");
                break;
            case 3:
                    response.sendRedirect("index.html");
                break;
        }
    }
}
