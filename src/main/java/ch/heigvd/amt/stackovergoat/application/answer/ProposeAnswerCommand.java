package ch.heigvd.amt.stackovergoat.application.answer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeAnswerCommand {
    @Builder.Default
    private String author = "Anonymous";

    @Builder.Default
    private String text = "No content";

    @Builder.Default
    private String questionId = "No content";
}
