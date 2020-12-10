package ch.heigvd.amt.stackovergoat.ui.web.comment;

import ch.heigvd.amt.stackovergoat.application.ServiceRegistry;
import ch.heigvd.amt.stackovergoat.application.comment.CommentFacade;
import ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate.CurrentUserDTO;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RemoveQuestionCommandEndpoint", urlPatterns = "/removeAnswerComment.do")
public class RemoveAnswerCommentEndpoint extends HttpServlet {

    @Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;
    private CommentFacade commentFacade;

    @Override
    public void init() throws ServletException {
        super.init();
        commentFacade = serviceRegistry.getAnswerCommentFacade();
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CurrentUserDTO user = (CurrentUserDTO)req.getSession().getAttribute("currentUser");

        commentFacade.remove(req.getParameter("commentId"));
        resp.sendRedirect("./profile");
    }
}
