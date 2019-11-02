package servlet;

import ejb.ItemBean;
import jdk.nashorn.internal.ir.RuntimeNode;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MostraItems")
public class ServletTestWeb extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean itemb;

    public ServletTestWeb() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        if (request.getParameter("fill") != null) {
            itemb.insert_item_test();
            out.println("<h1>Populate: OK!</h1>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
