package ch.heigvd.amt.stackovergoat.application.statistics;

import ch.heigvd.amt.stackovergoat.domain.question.IQuestionRepository;
import ch.heigvd.amt.stackovergoat.domain.user.IUserRepository;

public class StatsFacade {
    private IQuestionRepository questionRepository;
    private IUserRepository userRepository;

    public StatsFacade(IQuestionRepository questionRepository, IUserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;

    }

    public StatsDTO getStats() {
        return StatsDTO.builder()
                .nbQuestion(questionRepository.getSize())
                .nbUser(userRepository.getSize())
                .build();
    }
}
