package ch.heigvd.amt.stackovergoat.application.answer;

import ch.heigvd.amt.stackovergoat.application.comment.CommentsQuery;
import ch.heigvd.amt.stackovergoat.domain.answer.AnswerId;
import ch.heigvd.amt.stackovergoat.domain.answer.IAnswerRepository;
import ch.heigvd.amt.stackovergoat.domain.answer.Answer;
import ch.heigvd.amt.stackovergoat.domain.comment.ICommentRepository;
import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;
import ch.heigvd.amt.stackovergoat.domain.vote.IVoteRepository;
import ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception.IntegrityConstraintViolationException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerFacade {
    private IAnswerRepository answerRepository;
    private ICommentRepository commentRepository;
    private  IVoteRepository voteRepository;

    public AnswerFacade(IAnswerRepository answerRepository, ICommentRepository commentRepository, IVoteRepository voteRepository) {
        this.answerRepository = answerRepository;
        this.commentRepository = commentRepository;
        this.voteRepository = voteRepository;
    }

    public void proposeAnswer(ProposeAnswerCommand command) {
        if(command != null) {
            Answer submittedAnswer = Answer.builder()
                    .questionId(new QuestionId(command.getQuestionId()))
                    .author(command.getAuthor())
                    .text(command.getText())
                    .build();
            try {
                answerRepository.save(submittedAnswer);
            } catch (IntegrityConstraintViolationException e) {
                e.printStackTrace();
            }
        }
    }

    public AnswersDTO getAnswers(AnswersQuery query) {
        Collection<Answer> allAnswers = answerRepository.find(query);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream()
                .map(answer -> AnswersDTO.AnswerDTO.builder()
                        .id(answer.getId().asString())
                        .author(answer.getAuthor())
                        .idQuestion(answer.getQuestionId().asString())
                        .text(answer.getText())
                        .comments(commentRepository.find(
                                CommentsQuery.builder()
                                        .subjectId(answer.getId().asString())
                                        .build()).stream().collect(Collectors.toList()))
                        .nbrDownVotes(voteRepository.findAll().stream().filter(vote -> {
                            return !vote.isUpVote() && vote.getSubjectId().equals(answer.getId().asString());
                        }).collect(Collectors.toList()).size())
                        .nbrUpVotes(voteRepository.findAll().stream().filter(vote -> {
                            return vote.isUpVote() && vote.getSubjectId().equals(answer.getId().asString());
                        }).collect(Collectors.toList()).size())
                        .build())
                .collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }

    public AnswersDTO getAllAnswers() {
        Collection<Answer> allAnswers = answerRepository.findAll();

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream()
                .map(answer -> AnswersDTO.AnswerDTO.builder()
                        .text(answer.getText())
                        .author(answer.getAuthor())
                        .build())
                .collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }

    public void remove(String id) {
        answerRepository.remove(new AnswerId(id));
    }
}
