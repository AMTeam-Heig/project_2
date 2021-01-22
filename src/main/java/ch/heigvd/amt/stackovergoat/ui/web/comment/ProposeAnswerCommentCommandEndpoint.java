package ch.heigvd.amt.stackovergoat.ui.web.comment;

import ch.heig.amt.gamification.api.dto.Event;
import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.comment.CommentFacade;
import ch.heigvd.amt.stackovergoat.application.comment.ProposeCommentCommand;
import ch.heigvd.amt.stackovergoat.application.gamification.Events;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationQuery;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ProposeAnswerCommentCommandEndpoint", urlPatterns = "/submitAnswerComment.do")
public class ProposeAnswerCommentCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private CommentFacade commentFacade;// = serviceRegistry.getIdentityManagementFacade();

    GamificationQuery gamificationQuery = new GamificationQuery();

    @Override
    public void init() throws ServletException {
        super.init();
        commentFacade = serviceRegistry.getAnswerCommentFacade();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO user = (CurrentUserDTO)req.getSession().getAttribute("currentUser");
        ProposeCommentCommand command = null;

        if (user != null && req.getParameter("comment") != null && !req.getParameter("comment").equals("")) {
            command = ProposeCommentCommand.builder()
                    .subjectId(req.getParameter("answerId"))
                    .comment(req.getParameter("comment"))
                    .author(user.getUsername())
                    .build();

            Event event = new Event()
                    .name(Events.ADD_COMMENT.toString())
                    .points(0)
                    .username(user.getUsername());

            gamificationQuery.createEvent(event);
        }

        commentFacade.proposeComment(command);
        resp.sendRedirect("/question?id=" + req.getParameter("questionId"));
    }
}
