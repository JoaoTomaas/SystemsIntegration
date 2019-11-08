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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet (name = "NewItemServlet",
        urlPatterns = "/NewItemServ")
@MultipartConfig (fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 15, maxRequestSize = 1024 * 1024 * 60)
public class NewItemServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    @EJB
    ItemBean ib;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginBean bean = (LoginBean) request.getSession(true).getAttribute("currentSessionUser");
        String user_mail = bean.getEmail();
        String nome = request.getParameter("nome");
        String category = request.getParameter("categoria");
        String country = request.getParameter("country");
        String price = request.getParameter("price");

        //Para guardar a fotografia
        Part img_part = request.getPart("file");
        String img_name = ImgPath(img_part);
        //String finalPath = "C:\\Users\\joaom\\Downloads\\Imagens\\Apresentação\\" + img_name;
        //File Path = new File(finalPath);
        //img_part.write(Path + File.separator);

        //System.out.println("PATHHHHHHH FINAL ->>>> " + finalPath);
        //System.out.println("PATHHHHH DO MAL ->>>> " + img_part);
        System.out.println("Nome da imagem -> " + img_name);

        ib.Inserir_Novo_Item(nome, category, country, Float.parseFloat(price), user_mail, img_name);

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<script type=\"text/javascript\">");
        out.println("alert('Novo item inserido com sucesso');");
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
