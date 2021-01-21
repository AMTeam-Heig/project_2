package ch.heigvd.amt.stackovergoat.ui.web.login;

import ch.heig.amt.gamification.api.dto.User;
import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationFacade;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationQuery;
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
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@WebServlet(name = "RegisterCommandEndpoint", urlPatterns = "/register.do")
public class RegisterCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();

    private IdentityManagementFacade identityManagementFacade;
    private GamificationFacade gamificationFacade;

    private GamificationQuery gamificationQuery = new GamificationQuery();

    @Override
    public void init() throws ServletException {
        super.init();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
        gamificationFacade = serviceRegistry.getGamificationFacade();
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

        User gamificationUser = new User()
                .username(request.getParameter("username"))
                .birthdate(LocalDate.now())
                .points(0)
                .role(1)
                .badges(Collections.emptyList());

        gamificationQuery.createUser(gamificationUser);

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
