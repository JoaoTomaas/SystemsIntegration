package servlet;

import data.Item;
import dto.ItemDTO;
import ejb.ItemBean;
import ejb.LoginBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@WebServlet (name = "DateServlet",
        urlPatterns = "/DateServ")
public class DateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dia = request.getParameter("dia");
        String mes = request.getParameter("mes");
        String ano = request.getParameter("ano");

        List <ItemDTO> lista = ib.Procurar_Items_Data(convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)));
        request.setAttribute("lista_data", lista);

        RequestDispatcher view = request.getRequestDispatcher("result.jsp");
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public Date convertToDate(int dia, int mes, int ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes - 1);
        calendario.set(Calendar.DAY_OF_MONTH, dia);

        Date data = calendario.getTime();
        return data;
    }

}