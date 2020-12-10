package ch.heigvd.amt.stackovergoat.application.question;

import ch.heigvd.amt.stackovergoat.application.answer.AnswersDTO;
import ch.heigvd.amt.stackovergoat.domain.comment.Comment;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.Collection;
import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class QuestionsDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class QuestionDTO {
        private String id;
        private String text;
        private String author;
        private Collection<Comment> comments;
        private int nbrDownVotes;
        private int nbrUpVotes;
    }

    @Singular
    private List<QuestionDTO> questions;
}
