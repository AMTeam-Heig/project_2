package ch.heigvd.amt.stackovergoat.domain.answer;

import ch.heigvd.amt.stackovergoat.application.answer.AnswersQuery;
import ch.heigvd.amt.stackovergoat.domain.IRepository;

import java.util.Collection;
import java.util.Optional;

public interface IAnswerRepository extends IRepository<Answer, AnswerId> {
    public Collection<Answer> find(AnswersQuery query);
    public Optional<Answer> findById(AnswerId answer);
    public Collection<Answer> findAll();
}
