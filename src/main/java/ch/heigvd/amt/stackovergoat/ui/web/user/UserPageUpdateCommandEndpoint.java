package ch.heigvd.amt.stackovergoat.ui.web.user;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdateFailedException;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdatePasswordCommand;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.profile.UpdateProfileCommand;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "UserPageUpdateCommandEndpoint", urlPatterns = "/profile.up")
public class UserPageUpdateCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();

    private IdentityManagementFacade identityManagementFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        identityManagementFacade = serviceRegistry.getIdentityManagementFacade();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //request.getSession().removeAttribute("errors");

        try {
            UpdateProfileCommand updateProfileCommand = UpdateProfileCommand.builder()
                    .username(request.getSession().getAttribute("currentUsername").toString())
                    .lastname(request.getParameter("newLastname"))
                    .firstname(request.getParameter("newFirstname"))
                    .email(request.getParameter("newEmail"))
                    .build();

            CurrentUserDTO currentUserDTO = identityManagementFacade.updateProfile(updateProfileCommand);

            request.getSession().setAttribute("currentUser", currentUserDTO);

            response.sendRedirect("./profile");
        } catch (UpdateFailedException e) {
            request.getSession().setAttribute("errors", List.of(e.getMessage()));
            response.sendRedirect("./profile");
        }

    }

}
