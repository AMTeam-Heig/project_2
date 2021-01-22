package ch.heigvd.amt.stackovergoat.ui.web.user;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.answer.AnswerFacade;
import ch.heigvd.amt.stackovergoat.application.answer.AnswersDTO;
import ch.heigvd.amt.stackovergoat.application.answer.AnswersQuery;
import ch.heigvd.amt.stackovergoat.application.comment.CommentFacade;
import ch.heigvd.amt.stackovergoat.application.comment.CommentsDTO;
import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.application.gamification.GamificationQuery;
import ch.heigvd.amt.stackovergoat.application.question.QuestionFacade;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsDTO;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsQuery;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserPageEndPoint", urlPatterns = "/profile")
public class UserPageEndPoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;
    private AnswerFacade answerFacade;
    private QuestionFacade questionFacade;
    private CommentFacade commentAFacade;
    private CommentFacade commentQFacade;

    GamificationQuery gamificationQuery = new GamificationQuery();

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
        answerFacade = serviceRegistry.getAnswerFacade();
        commentAFacade = serviceRegistry.getAnswerCommentFacade();
        commentQFacade = serviceRegistry.getQuestionCommentFacade();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object errors = req.getSession().getAttribute("errors");

        QuestionsQuery questionsQuery = QuestionsQuery.builder()
                .author(req.getSession().getAttribute("currentUsername").toString())
                .build();

        CommentsQuery commentsQuery = CommentsQuery.builder()
                .author(req.getSession().getAttribute("currentUsername").toString())
                .build();

        AnswersQuery answersQuery = AnswersQuery.builder()
                .author(req.getSession().getAttribute("currentUsername").toString())
                .build();

        QuestionsDTO questionsDTO = questionFacade.getQuestions(questionsQuery);
        CommentsDTO commentsADTO = commentAFacade.getComments(commentsQuery);
        CommentsDTO commentsQDTO = commentQFacade.getComments(commentsQuery);
        AnswersDTO answersDTO = answerFacade.getAnswers(answersQuery);

        req.setAttribute("questions", questionsDTO);
        req.setAttribute("nbrQuestions", questionsDTO.getQuestions().size());
        req.setAttribute("commentsA", commentsADTO);
        req.setAttribute("commentsQ", commentsQDTO);
        req.setAttribute("nbrComments", commentsADTO.getComments().size() + commentsQDTO.getComments().size());
        req.setAttribute("answers", answersDTO);
        req.setAttribute("nbrAnswers", answersDTO.getAnswers().size());
        req.setAttribute("badges", gamificationQuery.getUser(req.getSession().getAttribute("currentUsername").toString()).getBadges());

        req.setAttribute("errors", errors);
        req.getSession().removeAttribute("errors");
        req.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(req, resp);
    }
}
