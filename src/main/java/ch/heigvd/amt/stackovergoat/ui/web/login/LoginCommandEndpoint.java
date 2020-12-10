package ch.heigvd.amt.stackovergoat.ui.web.login;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthentificationFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginCommandEndpoint", urlPatterns = "/login.do")
public class LoginCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private IdentityManagementFacade identityManagementFacade;// = serviceRegistry.getIdentityManagementFacade();

    @Override
    public void init() throws ServletException {
        super.init();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");

        CurrentUserDTO currentUserDTO = null;

        AuthenticateCommand authenticateCommand = AuthenticateCommand.builder()
                .username(req.getParameter("username"))
                .clearTextPassword(req.getParameter("clearTextPassword"))
                .build();

        try {
            currentUserDTO = identityManagementFacade.authenticate(authenticateCommand);
            req.getSession().setAttribute("currentUser", currentUserDTO);
            req.getSession().setAttribute("currentUsername", currentUserDTO.getUsername());
            String targetUrl = (String)req.getSession().getAttribute("targetUrl");
            targetUrl = (targetUrl != null) ? targetUrl : "./home";
            resp.sendRedirect(targetUrl);
            return;
        } catch (AuthentificationFailedException e) {
            req.getSession().setAttribute("errors", List.of(e.getMessage()));
            resp.sendRedirect("./login");
            return;
        }
    }
}
