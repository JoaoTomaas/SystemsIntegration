package servlet;


import data.Item;
import ejb.ItemBean;
import ejb.LoginBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "LogoutServlet",
        urlPatterns = "/LogoutServ")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessao = request.getSession(true);
        sessao.getAttribute("currentSessionUser");
        sessao.invalidate();
        logger.debug("Invalida a sessao");
        response.sendRedirect("index.jsp");

        //RequestDispatcher view = request.getRequestDispatcher("index.jsp");
        //view.forward(request, response);

    }
}
