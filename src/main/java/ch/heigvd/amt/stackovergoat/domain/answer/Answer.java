package ch.heigvd.amt.stackovergoat.domain.answer;

import ch.heigvd.amt.stackovergoat.domain.IEntity;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Answer implements IEntity<Answer, AnswerId> {

    @Setter(AccessLevel.NONE)
    private AnswerId id;
    private QuestionId questionId;
    private String author;
    private String text;
    private List<Comment> comments;

    @Override
    public Answer deepClone() {
        return this.toBuilder()
                .id(new AnswerId(id.asString()))
                .build();
    }

    public static class AnswerBuilder {
        public Answer build() {
            if(questionId == null) {
                // TODO throw exception
            }

            if(id == null) {
                id = new AnswerId();
            }

            if(text == null) {
                text = "";
            }

            return new Answer(id, questionId, author, text, null);
        }
    }

    public void comment(Comment comment) {
        comments.add(comment);
    }
}
