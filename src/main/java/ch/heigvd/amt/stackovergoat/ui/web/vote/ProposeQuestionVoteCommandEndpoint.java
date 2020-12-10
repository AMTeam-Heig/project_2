package ch.heigvd.amt.stackovergoat.ui.web.vote;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.vote.VoteFacade;
import ch.heigvd.amt.stackovergoat.application.vote.ProposeVoteCommand;
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

import static javax.servlet.http.HttpServletResponse.SC_FORBIDDEN;

@WebServlet(name = "SubmitQuestionVoteCommandHandler", urlPatterns = "/submitQuestionVote.do")
public class ProposeQuestionVoteCommandEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;
    private VoteFacade voteFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        voteFacade = serviceRegistry.getQuestionVoteFacade();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO user = (CurrentUserDTO)req.getSession().getAttribute("currentUser");
        ProposeVoteCommand command = null;

        if (user != null && req.getParameter("vote") != null && !req.getParameter("vote").equals("")) {
            command = ProposeVoteCommand.builder()
                    .subjectId(req.getParameter("questionId"))
                    .isUpVote(req.getParameter("vote").equals("+"))
                    .userId(user.getId())
                    .isForAnswer(false)
                    .build();
        }
        try {
            voteFacade.proposeVote(command);
            req.getSession().setAttribute("error", null);
        } catch (Exception e) {
            req.getSession().setAttribute("error", "You can vote only once for each question or answer !");
        }
        resp.sendRedirect("/question?id=" + req.getParameter("questionId"));
    }
}
