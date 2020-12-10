package ch.heigvd.amt.stackovergoat.application.vote;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Builder
@Getter
@EqualsAndHashCode
public class VotesQuery {
    @Builder.Default
    private String idUser = "";

    @Builder.Default
    private String idSubject = "";

    @Builder.Default
    private int voteValue = 0;
}
