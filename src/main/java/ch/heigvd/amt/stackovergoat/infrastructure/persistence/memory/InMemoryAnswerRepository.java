package ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory;

import ch.heigvd.amt.stackovergoat.application.answer.AnswersQuery;
import ch.heigvd.amt.stackovergoat.domain.answer.IAnswerRepository;
import ch.heigvd.amt.stackovergoat.domain.answer.Answer;
import ch.heigvd.amt.stackovergoat.domain.answer.AnswerId;
import ch.heigvd.amt.stackovergoat.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryAnswerRepository extends InMemoryRepository<Answer, AnswerId> implements IAnswerRepository {
    @Override
    public Collection<Answer> find(AnswersQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromAuthor  = (!query.getAuthor().equals(""));
        boolean fromId      = (!query.getIdQuestion().equals(""));
        boolean fromText    = (!query.getText().equals(""));

        if (!(fromAuthor || fromId || fromText)) {
            return findAll();
        }
        List<Answer> answers = findAll().stream()
                .filter(answer -> (
                        (fromAuthor && answer.getAuthor().equals(query.getAuthor()))              ||
                        (fromId     && answer.getQuestionId().asString().equals(query.getIdQuestion()))   ||
                        (fromText   && answer.getText().equals(query.getText()))))
                .collect(Collectors.toList());
        return answers;
    }

}
