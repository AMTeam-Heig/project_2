package ch.heigvd.amt.stackovergoat.ui.web.filter;

import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/*")
public class AuthorizationFilter implements Filter {
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if(isPublicResource(request.getRequestURI())) {
            chain.doFilter(req, resp);
            return;
        }

        CurrentUserDTO currentUser = (CurrentUserDTO)request.getSession().getAttribute("currentUser");

        if(currentUser == null) {
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);
            request.getSession().removeAttribute("targetUrl");

            ((HttpServletResponse) resp).sendRedirect("./login");
            return;
        }

        chain.doFilter(req, resp);
    }

    boolean isPublicResource(String URI) {
        if(URI.startsWith("/login")) {
            return true;
        }
        if(URI.startsWith("/register")) {
            return true;
        }
        if(URI.startsWith("/question")) {
            return true;
        }
        if(URI.startsWith("/home")) {
            return true;
        }
        if(URI.startsWith("/statistics")) {
            return true;
        }
        if(URI.startsWith("/assets")) {
            return true;
        }
        if(URI.startsWith("/comment")) {
            return true;
        }
        // TODO : set to false
        return false;
    }

}
