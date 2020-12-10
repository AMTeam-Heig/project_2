package ch.heigvd.amt.stackovergoat.ui.web.statistics;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.question.QuestionFacade;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsDTO;
import ch.heigvd.amt.stackovergoat.application.statistics.StatsDTO;
import ch.heigvd.amt.stackovergoat.application.statistics.StatsFacade;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "statisticsPageEndPoint" ,urlPatterns =  "/statistics")
public class statisticsPageEndPoint extends HttpServlet {


    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;// = ServiceRegistry.getServiceRegistry();
    private StatsFacade statsFacade;// = serviceRegistry.getIdentityManagementFacade();

    @Override
    public void init() throws ServletException {
        super.init();
        statsFacade = serviceRegistry.getStatsFacade();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatsDTO  statistiques= statsFacade.getStats();
        req.setAttribute("stats", statistiques);
        req.getRequestDispatcher("/WEB-INF/views/statistics.jsp").forward(req, resp);
    }
}
