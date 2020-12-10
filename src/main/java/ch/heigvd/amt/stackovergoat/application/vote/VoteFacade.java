package ch.heigvd.amt.stackovergoat.application.vote;

import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;
import ch.heigvd.amt.stackovergoat.domain.vote.Vote;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;
import ch.heigvd.amt.stackovergoat.domain.vote.VoteId;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class VoteFacade {
    private IVoteRepository voteRepository;

    public VoteFacade(IVoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public void proposeVote(ProposeVoteCommand command) {
        if(command != null) {
            Vote submittedVote = Vote.builder()
                    .subjectId(command.getSubjectId())
                    .userId(command.getUserId())
                    .isUpVote(command.isUpVote())
                    .isForAnswer(command.isForAnswer())
                    .build();
            try {
                voteRepository.save(submittedVote);
            } catch (IntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
        }
    }

    public VotesDTO getVotes(VotesQuery query) {
        Collection<Vote> allVotes = voteRepository.find(query);

        List<VotesDTO.VoteDTO> allVotesDTO = allVotes.stream()
                .map(vote -> VotesDTO.VoteDTO.builder()
                        .idSubject(vote.getSubjectId())
                        .idUser(vote.getUserId())
                        .isUpVote(vote.isUpVote())
                        .isForAnswer(vote.isForAnswer())
                        .build())
                .collect(Collectors.toList());

        return VotesDTO.builder()
                .votes(allVotesDTO)
                .build();
    }

    public VotesDTO getAllVotes() {
        Collection<Vote> allVotes = voteRepository.findAll();

        List<VotesDTO.VoteDTO> allVotesDTO = allVotes.stream()
                .map(vote -> VotesDTO.VoteDTO.builder()
                        .idSubject(vote.getSubjectId())
                        .idUser(vote.getUserId())
                        .isUpVote(vote.isUpVote())
                        .isForAnswer(vote.isForAnswer())
                        .build())
                .collect(Collectors.toList());

        return VotesDTO.builder()
                .votes(allVotesDTO)
                .build();
    }

    public void remove(String id) {
        voteRepository.remove(new VoteId(id));
    }
}
