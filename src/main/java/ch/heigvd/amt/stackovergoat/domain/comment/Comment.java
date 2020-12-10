package ch.heigvd.amt.stackovergoat.domain.comment;

import ch.heigvd.amt.stackovergoat.domain.IEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder(toBuilder = true)
public class Comment implements IEntity<Comment, CommentId> {

    @Setter(AccessLevel.NONE)
    private CommentId id;
    private boolean isForAnswer = true;
    private String subjectId;
    private String author;
    private String comment;
    private String userId;

    @Override
    public Comment deepClone() {
        return this.toBuilder()
                .id(new CommentId(id.asString()))
                .build();
    }

    public static class CommentBuilder {
        public Comment build() {
            if (subjectId == null) {
                // TODO throw exception
            }

            if (id == null) {
                id = new CommentId();
            }

            if (comment == null) {
                comment = "";
            }

            return new Comment(id, isForAnswer, subjectId, author, comment, userId);
        }
    }
}
