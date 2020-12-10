package ch.heigvd.amt.stackovergoat.domain.question;

import ch.heigvd.amt.stackovergoat.application.question.QuestionsQuery;
import ch.heigvd.amt.stackovergoat.domain.IRepository;

import java.util.Collection;
import java.util.Optional;

public interface IQuestionRepository extends IRepository<Question, QuestionId> {
    public Collection<Question> find(QuestionsQuery query);
    public Collection<Question> findAll();
    public Optional<Question> findById(QuestionId question);
}
