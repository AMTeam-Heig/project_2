package ch.heigvd.amt.stackovergoat.ui.web.question;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.question.ProposeQuestionCommand;
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
import java.util.Arrays;

@WebServlet(name = "QuestionsQueryEndpoint", urlPatterns = "/home")
public class QuestionsQueryEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private QuestionFacade questionFacade;// = serviceRegistry.getIdentityManagementFacade();

    @Override
    public void init() throws ServletException {
        super.init();
        questionFacade = serviceRegistry.getQuestionFacade();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QuestionsDTO questionsDTO;

        if (req.getParameter("search") != null && !req.getParameter("search").isEmpty()) {
            QuestionsQuery questionsQuery = QuestionsQuery.builder()
                    .words(req.getParameter("search"))
                    .build();
            questionsDTO = questionFacade.getQuestions(questionsQuery);
        } else {
            questionsDTO = questionFacade.getAllQuestions();
        }

        req.setAttribute("questions", questionsDTO);
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }
}
