package ch.heigvd.amt.stackovergoat.ui.web.administration;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.comment.ProposeCommentCommand;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationQuery;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthenticateCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.AuthentificationFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stackovergoat.application.user.ProposeUserCommand;
import ch.heigvd.amt.stackovergoat.application.user.UserFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AdministrationCommandEndpoint", urlPatterns = "/administration.do")
public class AdministrationCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;
    private IdentityManagementFacade identityManagementFacade;
    private GamificationQuery gamificationQuery = new GamificationQuery();
    private UserFacade userFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
        userFacade = serviceRegistry.getUserFacade();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("errors");

        Role r = gamificationQuery.getUser(((CurrentUserDTO) req.getSession().getAttribute("currentUser")).getUsername()).getRole();
        // TODO check this
        if (r.getRoleValue().equals(0)) {
            System.out.println("I am admin.");

            ProposeUserCommand command = ProposeUserCommand.builder()
                    .username(req.getParameter("username"))
                    .firstname(req.getParameter("firstname"))
                    .lastname(req.getParameter("lastname"))
                    .email(req.getParameter("email"))
                    .clearTextPassword(req.getParameter("clearTextPassword"))
                    .role(req.getParameter("role"))
                    .build();
            userFacade.proposeUser(command);
        }

        // TODO decide where we want to go next
        resp.sendRedirect("/administration");
    }
}
