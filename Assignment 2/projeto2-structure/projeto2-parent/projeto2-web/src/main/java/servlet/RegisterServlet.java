package servlet;

import ejb.RegisterBean;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;

@WebServlet (urlPatterns = "/Register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    RegisterBean regb;

    public RegisterServlet() {super();}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String pass1 = request.getParameter("password1");
        String pass2 = request.getParameter("password2");
        String nome = request.getParameter("nome");
        String pais = request.getParameter("pais");
        int age = Integer.parseInt(request.getParameter("idade"));
        try {
            if(pass1.equals(pass2)) {
                if (regb.UserRegister(email, pass1, pais, nome, age) == 0) {
                    response.sendRedirect("login.jsp");

                } else {
                    response.sendRedirect("index.html");
                }
            }
            else {
                PrintWriter out = response.getWriter();
                response.setContentType("text/html");

                //response.sendRedirect("fail.jsp");
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Passwords nao sao iguais');");
                out.println("location='register.jsp';");
                out.println("</script>");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


    }

}
