package ch.heigvd.amt.stackovergoat.domain.question;

import ch.heigvd.amt.stackovergoat.domain.IEntity;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class Question implements IEntity<Question, QuestionId> {

    private QuestionId id;
    private String author;
    private String text;
    private List<Comment> comments;

    @Override
    public Question deepClone() {
        return this.toBuilder()
                .id(new QuestionId(id.asString()))
                .build();
    }

    public boolean containsWords(String searchText) {
        return text.toLowerCase().contains(searchText.toLowerCase());
    }

    public static class QuestionBuilder {
        public Question build() {
            if(id == null) {
                id = new QuestionId();
            }

            if(text == null) {
                text = "";
            }

            return new Question(id, author, text, null);
        }
    }

    public void comment(Comment comment) {
        comments.add(comment);
    }
}
