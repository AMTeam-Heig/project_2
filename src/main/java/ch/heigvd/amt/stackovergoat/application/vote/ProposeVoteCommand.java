package ch.heigvd.amt.stackovergoat.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeVoteCommand {
    @Builder.Default
    private String userId = "No content";

    @Builder.Default
    private String subjectId = "No content";

    @Builder.Default
    private boolean isForAnswer = true;

    @Builder.Default
    private boolean isUpVote = true;
}
