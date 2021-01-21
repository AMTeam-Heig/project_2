package ch.heigvd.amt.stackovergoat.ui.web.question;

import ch.heig.amt.gamification.api.dto.Event;
import ch.heig.amt.gamification.api.dto.User;
import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.gamification.Events;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationQuery;
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
import java.time.LocalDate;

@WebServlet(name = "ProposeQuestionCommandEndpoint", urlPatterns = "/submitQuestion.do")
public class ProposeQuestionCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private QuestionFacade questionFacade;// = serviceRegistry.getIdentityManagementFacade();

    private GamificationQuery gamificationQuery = new GamificationQuery();

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

            Event event = new Event()
                    .name(Events.ADD_QUESTION.toString())
                    .points(1)
                    .username(user.getUsername());

            gamificationQuery.createEvent(event);

        }

        questionFacade.proposeQuestion(command);
        resp.sendRedirect("./home");
    }
}
