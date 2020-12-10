package ch.heigvd.amt.stackovergoat.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class VotesDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class VoteDTO {
        private String idSubject;
        private String idUser;
        private boolean isForAnswer;
        private boolean isUpVote;
    }

    @Singular
    private List<VoteDTO> votes;
}
