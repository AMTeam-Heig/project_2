package ch.heigvd.amt.stackovergoat.ui.web.login;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegisterCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.login.RegistrationFailedException;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();

    private IdentityManagementFacade identityManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getSession().removeAttribute("errors");

        RegisterCommand registerCommand = RegisterCommand.builder()
                .username(request.getParameter("username"))
                .firstname(request.getParameter("firstname"))
                .lastname(request.getParameter("lastname"))
                .email(request.getParameter("email"))
                .clearTextPassword(request.getParameter("clearTextPassword"))
                .build();
        try {
            identityManagementFacade.register(registerCommand);
            request.setAttribute("username", registerCommand.getUsername());
            request.setAttribute("clearTextPassword", registerCommand.getClearTextPassword());
            request.getRequestDispatcher("./login.do").forward(request, response);

        } catch (RegistrationFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("./login");

        }

    }
}
