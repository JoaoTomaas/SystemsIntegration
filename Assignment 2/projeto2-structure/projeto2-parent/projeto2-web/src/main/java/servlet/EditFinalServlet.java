package servlet;

import data.Item;
import ejb.ItemBean;
import ejb.LoginBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "EditFinalServlet",
        urlPatterns = "/EditFinalServ")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 15, maxRequestSize = 1024 * 1024 * 60)
public class EditFinalServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String category = request.getParameter("categoria");
        String country = request.getParameter("country");
        float price = Float.parseFloat(request.getParameter("price"));

        Part img_part = request.getPart("file");
        String img_name = ImgPath(img_part);

        if (!img_name.equals("")) {
            //System.out.println("PATH DO MAL ->> " + img_name);
            ib.Editar_Item_Info(id, nome, category, country, price, img_name);
        }
        else{
            ib.Editar_Item_Img(id, nome, category, country, price);
        }

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        //response.sendRedirect("fail.jsp");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Informação do item atualizada com sucesso');");
        out.println("location='menu.jsp';");
        out.println("</script>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    public String ImgPath (Part part){
        String contentDisposition = part.getHeader("content-disposition");
        String[] separa_content = contentDisposition.split(";");
        String path = "";
        for (String st: separa_content){
            if (st.contains("filename")){
                path = st.substring(st.indexOf("=") + 2, st.length() - 1); // +2 para ignorar =" e -1 para ignorar "
            }
        }

        return path;
    }

}