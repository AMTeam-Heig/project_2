package stackovergoat.application;

import ch.heigvd.amt.stackovergoat.application.comment.CommentFacade;
import ch.heigvd.amt.stackovergoat.application.comment.CommentsDTO;
import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.application.comment.ProposeCommentCommand;
import ch.heigvd.amt.stackovergoat.application.question.ProposeQuestionCommand;
import ch.heigvd.amt.stackovergoat.application.question.QuestionFacade;
import ch.heigvd.amt.stackovergoat.application.vote.VoteFacade;
import ch.heigvd.amt.stackovergoat.domain.comment.CommentId;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;
import ch.heigvd.amt.stackovergoat.domain.question.IQuestionRepository;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryCommentRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory.InMemoryQuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentTest {
    private final int TEST_NBR = 1000;

    private final String QUESTION_TEXT = "Is this really a question ?";
    private final String QUESTION_AUTHOR = "Question author";

    private final String COMMENT_TEXT = "This is a comment.";
    private final String COMMENT_AUTHOR = "Vote author";

    private static IQuestionRepository questionRepository;
    private static QuestionFacade questionFacade;

    private static ICommentRepository commentRepository;
    private static CommentFacade commentFacade;

    private static IVoteRepository voteRepository;
    private static VoteFacade voteFacade;

    private final ProposeQuestionCommand proposeQuestionCommand = ProposeQuestionCommand.builder()
            .author(QUESTION_AUTHOR)
            .text(QUESTION_TEXT)
            .build();

    private final ProposeCommentCommand proposeCommentCommand = ProposeCommentCommand.builder()
            .author(COMMENT_AUTHOR)
            .comment(COMMENT_TEXT)
            .build();

    @BeforeEach
    public void initialization() {
        questionRepository = new InMemoryQuestionRepository();
        questionFacade = new QuestionFacade(questionRepository, commentRepository, voteRepository);

        commentRepository = new InMemoryCommentRepository();
        commentFacade = new CommentFacade(commentRepository);
    }

    @Test
    public void proposingACommentShouldAddItToFacade() {
        questionFacade.proposeQuestion(proposeQuestionCommand);
        assertDoesNotThrow(() -> commentFacade.proposeComment(ProposeCommentCommand.builder()
                .subjectId(questionFacade.getAllQuestions().getQuestions().get(0).getId())
                .comment("Aw heellllll nooooo!")
                .author("Walidou")
                .build()));
        CommentsQuery commentsQuery = CommentsQuery.builder()
                .commentsAnswer(false)
                .build();
        CommentsDTO commentsDTO = commentFacade.getComments(commentsQuery);
        assertFalse(commentFacade.getAllComments().getComments().isEmpty());
        assertFalse(commentsDTO.getComments().isEmpty());
    }

    @Test
    public void gettingCommentFromQuestionIdShouldWork() {
        questionFacade.proposeQuestion(proposeQuestionCommand);
        assertDoesNotThrow(() -> commentFacade.proposeComment(ProposeCommentCommand.builder()
                .subjectId(questionFacade.getAllQuestions().getQuestions().get(0).getId())
                .comment("Aw heellllll nooooo!")
                .author("Walidou")
                .build()));
        CommentsQuery commentsQuery = CommentsQuery.builder()
                .subjectId(questionFacade.getAllQuestions().getQuestions().get(0).getId())
                .build();
        CommentsDTO commentsDTO = commentFacade.getComments(commentsQuery);

        assertFalse(commentFacade.getAllComments().getComments().isEmpty());
        assertFalse(commentsDTO.getComments().isEmpty());
    }

    @Test
    public void commentFacadeShouldStoreCorrectComment() {
        commentFacade.proposeComment(proposeCommentCommand);
        assertEquals(COMMENT_AUTHOR, commentFacade.getAllComments().getComments().get(0).getAuthor());
        assertEquals(COMMENT_TEXT, commentFacade.getAllComments().getComments().get(0).getComment());
    }

    @Test
    public void commentIdShouldBeUnique() {
        LinkedList<CommentId> ids = new LinkedList<>();
        for (int i = 0; i < TEST_NBR; ++i) {
            ids.add(new CommentId());
        }
        for (int i = 0; i < TEST_NBR; ++i) {
            assertFalse(ids.contains(new CommentId()));
        }
        System.out.println("id : " + new CommentId().asString().length());
    }

    @Test
    public void commentIdShouldBeCreatedCorrectly() {
        UUID uuid = UUID.randomUUID();
        String stringUuid = UUID.randomUUID().toString();

        CommentId commentId = new CommentId(uuid);
        CommentId stringCommentId = new CommentId(stringUuid);

        assertEquals(uuid.toString(), commentId.asString());
        assertEquals(stringUuid, stringCommentId.asString());
    }

    @Test
    public void getCommentsShouldReturnANonEmptyCollection() {
        assertDoesNotThrow(() -> commentFacade.proposeComment(proposeCommentCommand));
    }

    @Test
    public void findAllAndFindWithNullQueryShouldBeTheSame() {
        assertEquals(commentFacade.getComments(null), commentFacade.getAllComments());
    }

    @Test
    public void findWithQueryShouldReturnSomething() {
        assertDoesNotThrow(() -> commentFacade.proposeComment(proposeCommentCommand));
        assertEquals(commentFacade.getComments(CommentsQuery.builder().subjectId(commentFacade.getAllComments().getComments().get(0).getIdSubject()).build()), commentFacade.getAllComments());
    }
}
