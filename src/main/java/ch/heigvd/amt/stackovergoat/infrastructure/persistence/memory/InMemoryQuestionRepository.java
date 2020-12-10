package ch.heigvd.amt.stackovergoat.infrastructure.persistence.memory;
import ch.heigvd.amt.stackovergoat.application.question.QuestionsQuery;
import ch.heigvd.amt.stackovergoat.domain.question.IQuestionRepository;
import ch.heigvd.amt.stackovergoat.domain.question.Question;
import ch.heigvd.amt.stackovergoat.domain.question.QuestionId;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryQuestionRepository extends InMemoryRepository<Question, QuestionId> implements IQuestionRepository {
    @Override
    public Collection<Question> find(QuestionsQuery query) {
        if (query == null) {
            return findAll();
        }
        boolean fromAuthor  = (!query.getAuthor().equals(""));
        boolean fromId      = (!query.getIdQuestion().equals(""));
        boolean fromText    = (!query.getText().equals(""));
        boolean fromWords   = (!query.getWords().equals(""));

        if (!(fromAuthor || fromId || fromText || fromWords)) {
            return findAll();
        }
        List<Question> questions = findAll().stream()
                .filter(question -> (
                        (fromAuthor && question.getAuthor().equals(query.getAuthor()))              ||
                        (fromId     && question.getId().asString().equals(query.getIdQuestion()))   ||
                        (fromText   && question.getText().equals(query.getText()))                  ||
                        (fromWords  && question.containsWords(query.getWords()))))
                .collect(Collectors.toList());
        return questions;
    }

    @Override
    public int getSize() {
        return 0;
    }

}
