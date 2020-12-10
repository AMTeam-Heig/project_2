package ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory;

import ch.heigvd.amt.stackovergoat.application.vote.VotesQuery;
import ch.heigvd.amt.stackovergoat.domain.vote.Vote;
import ch.heigvd.amt.stackovergoat.domain.vote.VoteId;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryVoteRepository extends InMemoryRepository<Vote, VoteId> implements IVoteRepository {
    @Override
    public Collection<Vote> find(VotesQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromUser    = (!query.getIdUser().equals(""));
        boolean fromSubject = (!query.getIdSubject().equals(""));

        if (!(fromUser || fromSubject)) {
            return findAll();
        }
        List<Vote> votes = findAll().stream()
                .filter(vote -> (
                        (fromUser    && vote.getUserId().equals(query.getIdUser()))              ||
                        (fromSubject && vote.getSubjectId().equals(query.getIdSubject()))))
                .collect(Collectors.toList());
        return votes;
    }

}
