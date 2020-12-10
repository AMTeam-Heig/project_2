package ch.heigvd.amt.stackovergoat.domain.vote;

import ch.heigvd.amt.stackovergoat.application.vote.VotesQuery;
import ch.heigvd.amt.stackovergoat.domain.IRepository;

import java.util.Collection;
import java.util.Optional;

public interface IVoteRepository extends IRepository<Vote, VoteId> {
    public Collection<Vote> find(VotesQuery query);
    public Optional<Vote> findById(VoteId voteId);
    public Collection<Vote> findAll();
}
