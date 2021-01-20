package ch.heigvd.amt.stackovergoat.ui.web.filter;

//@WebFilter(filterName = "RulesFilter", urlPatterns = "/*")
public class RulesFilter/* implements Filter*/ {
    /*@Inject
    @Named("ServiceRegistry")
    private ServiceRegistry serviceRegistry;
    private AnswerFacade answerFacade ;
    private QuestionFacade questionFacade ;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        answerFacade = serviceRegistry.getAnswerFacade();
        questionFacade = serviceRegistry.getQuestionFacade();
    }

    @Override
    public void doFilter(ServletRequest  req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        CurrentUserDTO currentUser = (CurrentUserDTO)request.getSession().getAttribute("currentUser");

        if(currentUser != null && isForbiddenResource(request, currentUser.getUsername())) {
            String targetUrl = request.getRequestURI();
            if(request.getQueryString() != null) {
                targetUrl += "?" + request.getQueryString();
            }
            request.getSession().setAttribute("targetUrl", targetUrl);
            request.getSession().removeAttribute("targetUrl");

            ((HttpServletResponse) resp).sendRedirect("./profile");
            return;
        } else {
            chain.doFilter(req, resp);
            return;
        }

    }

    private boolean isForbiddenResource(HttpServletRequest request, String username) {
        if (request.getRequestURI().contains("/submitAnswerVote")) {
            String subjectId = request.getParameter("answerId");
            String ownerUsernameForVote = answerFacade.getAnswers(
                    AnswersQuery.builder().idAnswer(subjectId).build())
                    .getAnswers().get(0).getAuthor();
            return username.equals(ownerUsernameForVote);
        }
        if (request.getRequestURI().contains("/submitQuestionVote")) {
            String subjectId = request.getParameter("questionId");
            String ownerUsernameForVote = questionFacade.getQuestions(
                    QuestionsQuery.builder().idQuestion(subjectId).build())
                    .getQuestions().get(0).getAuthor();
            return username.equals(ownerUsernameForVote);
        }
        return false;
    }

    @Override
    public void destroy() {

    }*/
}
