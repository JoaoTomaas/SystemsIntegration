package servlet;

import ejb.RegisterBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet (urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    RegisterBean regb;

    public RegisterServlet() {super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        String nome = request.getParameter("nome");
        String pais = request.getParameter("pais");
        /*if(regb.testeeee(email,pass)==1){
            response.getWriter().println("Nice");
        }
        else{
            response.getWriter().println("Hacker");
        }*/
        int age = Integer.parseInt(request.getParameter("idade"));
        try {
            if(regb.UserRegister(email,pass,pais,nome,age)==0){
                response.sendRedirect("login.jsp");

            }
            else{
                response.sendRedirect("index.html");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

}
