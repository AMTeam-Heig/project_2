package ch.heigvd.amt.stackovergoat.application.comment;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeCommentCommand {
    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String comment = "No content";

    @Builder.Default
    private String subjectId = "No content";

    @Builder.Default
    private boolean isForAnswer = true;
}
