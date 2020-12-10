package ch.heigvd.amt.stackovergoat.application.answer;

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
public class AnswersDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class AnswerDTO {
        private String id;
        private String idQuestion;
        private String text;
        private String author;
        private Collection<Comment> comments;
        private int nbrDownVotes;
        private int nbrUpVotes;
    }

    @Singular
    private List<AnswerDTO> answers;
}
