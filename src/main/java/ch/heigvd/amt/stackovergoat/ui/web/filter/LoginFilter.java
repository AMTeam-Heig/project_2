package ch.heigvd.amt.stackovergoat.ui.web.filter;

import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter", urlPatterns = "/login")
public class LoginFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        CurrentUserDTO currentUser = (CurrentUserDTO)request.getSession().getAttribute("currentUser");

        if(currentUser != null) {
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);
            request.getSession().removeAttribute("targetUrl");

            ((HttpServletResponse) resp).sendRedirect("./profile");
            return;
        }

        chain.doFilter(req, resp);
    }

}
