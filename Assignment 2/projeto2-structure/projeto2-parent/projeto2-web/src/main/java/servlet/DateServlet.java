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
        String nome = request.getParameter("nome");
        if(request.getParameter("sort")==null){
            List <ItemDTO> lista = ib.Procurar_Items_Data(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)));
            request.setAttribute("lista_data", lista);
            RequestDispatcher view = request.getRequestDispatcher("result.jsp");
            view.forward(request, response);
        }
        else {

            //System.out.println("testeeeeeeee "+request.getParameter("nome_aux"));
            int metodo=Integer.parseInt(request.getParameter("sort"));
            switch (metodo){
                case 1:
                    List <ItemDTO> lista = ib.Procurar_Items_Data1(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),1);
                    request.setAttribute("lista_data", lista);
                    RequestDispatcher view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 2:
                    lista = ib.Procurar_Items_Data2(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),1);
                    request.setAttribute("lista_data", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 3:
                    lista = ib.Procurar_Items_Data3(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),1);
                    request.setAttribute("lista_data", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 4:
                    lista = ib.Procurar_Items_Data1(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),0);
                    request.setAttribute("lista_data", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 5:
                    lista = ib.Procurar_Items_Data2(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),0);
                    request.setAttribute("lista_data", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;
                case 6:
                    lista = ib.Procurar_Items_Data3(nome,convertToDate(Integer.parseInt(dia), Integer.parseInt(mes), Integer.parseInt(ano)),0);
                    request.setAttribute("lista_data", lista);
                    view = request.getRequestDispatcher("result.jsp");
                    view.forward(request, response);
                    break;

            }

        }
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