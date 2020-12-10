package ch.heigvd.amt.stackovergoat.ui.web.question;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.IdentityManagementFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;
import ch.heigvd.amt.stackovergoat.application.question.ProposeQuestionCommand;
import ch.heigvd.amt.stackovergoat.application.question.QuestionFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProposeQuestionCommandEndpoint", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private QuestionFacade questionFacade;// = serviceRegistry.getIdentityManagementFacade();

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO user = (CurrentUserDTO)req.getSession().getAttribute("currentUser");
        ProposeQuestionCommand command = null;
        if (user != null && req.getParameter("text") != null && !req.getParameter("text").equals("")) {
            command = ProposeQuestionCommand.builder()
                    .text(req.getParameter("text"))
                    .author(user.getUsername())
                    .build();
        }

        questionFacade.proposeQuestion(command);
        resp.sendRedirect("./home");
    }
}
