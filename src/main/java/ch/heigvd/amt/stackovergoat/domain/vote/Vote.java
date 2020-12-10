package ch.heigvd.amt.stackovergoat.domain.vote;

import ch.heigvd.amt.stackovergoat.domain.IEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;

@Data
@Builder(toBuilder = true)
public class Vote implements IEntity<Vote, VoteId> {

    @Setter(AccessLevel.NONE)
    private VoteId id;
    private String subjectId;
    private String userId;
    private boolean isForAnswer;
    private boolean isUpVote;

    @Override
    public Vote deepClone() {
        return this.toBuilder()
                .id(new VoteId(id.asString()))
                .build();
    }

    public static class VoteBuilder {
        public Vote build() {
            if(subjectId == null) {
                // TODO throw exception
            }

            if(userId == null) {
                // TODO throw exception
            }

            if(id == null) {
                id = new VoteId();
            }

            return new Vote(id, subjectId, userId, isForAnswer, isUpVote);
        }
    }
}
