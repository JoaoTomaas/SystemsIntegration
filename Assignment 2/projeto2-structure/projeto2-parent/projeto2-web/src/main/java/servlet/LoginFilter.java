package servlet;

import ejb.LoginBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {

    final Logger logger = LoggerFactory.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession();
        String path = request.getContextPath();
        //System.out.println("debug--->"+loginURI);
        boolean loggedIn;
        if(session.getAttribute("currentSessionUser")!=null){
            loggedIn=true;
        }
        else {
            loggedIn=false;
        }
        logger.debug("Tenho sessao iniciada?" + loggedIn);
        String loginRequest = request.getRequestURI();
        logger.debug("Path ---> " + request.getRequestURI());
        if (loggedIn||loginRequest.equals(path+"/login.jsp")||loginRequest.equals(path+"/Login")||loginRequest.equals(path+"/index.html")||loginRequest.equals(path+"/index.jsp")||loginRequest.equals(path+"/register.jsp")||loginRequest.equals(path+"/Register")) {
            chain.doFilter(request, response);
        } else {
            response.sendRedirect(path+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }

}