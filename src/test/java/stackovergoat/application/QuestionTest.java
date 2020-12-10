package stackovergoat.application;

import ch.heigvd.amt.stackovergoat.application.comment.CommentFacade;
import ch.heigvd.amt.stackovergoat.application.question.ProposeQuestionCommand;
import ch.heigvd.amt.stackovergoat.application.question.QuestionFacade;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsDTO;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsQuery;
import ch.heigvd.amt.stackovergoat.application.user.ProposeUserCommand;
import ch.heigvd.amt.stackovergoat.application.user.UsersDTO;
import ch.heigvd.amt.stackovergoat.application.user.UsersQuery;
import ch.heigvd.amt.stackovergoat.application.vote.VoteFacade;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;
import ch.heigvd.amt.stackovergoat.domain.question.IQuestionRepository;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryCommentRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryQuestionRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryVoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionTest {
    private final String TEXT = "Is this really a question ?";
    private final String AUTHOR = "Author";

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    private static ICommentRepository commentRepository;
    private static CommentFacade commentFacade;

    private static IVoteRepository voteRepository;
    private static VoteFacade voteFacade;

    private final ProposeQuestionCommand proposeQuestionCommand = ProposeQuestionCommand.builder()
            .author(AUTHOR)
            .text(TEXT)
            .build();

    @BeforeEach
    public void initialization() {
        commentRepository = new InMemoryCommentRepository();
        commentFacade = new CommentFacade(commentRepository);

        voteRepository = new InMemoryVoteRepository();
        voteFacade = new VoteFacade(voteRepository);

        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository, commentRepository, voteRepository);
    }

    @Test
    public void proposingAQuestionShouldAddItToFacade() {
        assertDoesNotThrow(() -> questionFacade.proposeQuestion(proposeQuestionCommand));
        QuestionsQuery questionsQuery = QuestionsQuery.builder()
                .isQuestion(true)
                .build();
        QuestionsDTO questionsDTO = questionFacade.getQuestions(questionsQuery);
        assertFalse(questionFacade.getAllQuestions().getQuestions().isEmpty());
        // assertFalse(questionsDTO.getQuestions().isEmpty());
    }

    @Test
    public void userFacadeShouldStoreCorrectQuestion() {
        questionFacade.proposeQuestion(proposeQuestionCommand);
        assertEquals(AUTHOR, questionFacade.getAllQuestions().getQuestions().get(0).getAuthor());
        assertEquals(TEXT, questionFacade.getAllQuestions().getQuestions().get(0).getText());
    }

    @Test
    public void findAllAndFindWithNullQueryShouldBeTheSame() {
        assertEquals(questionFacade.getQuestions(null), questionFacade.getAllQuestions());
    }

    @Test
    public void getQuestionWithIdQueryShouldWork() {
        questionFacade.proposeQuestion(proposeQuestionCommand);
        String id = questionFacade.getAllQuestions().getQuestions().get(0).getId().toString();
        QuestionsDTO questionsDTO = questionFacade.getQuestions(QuestionsQuery.builder().idQuestion(id).build());

        assertEquals(1, questionsDTO.getQuestions().size());
    }
}
