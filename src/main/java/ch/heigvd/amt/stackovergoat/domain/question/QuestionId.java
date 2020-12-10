package ch.heigvd.amt.stackovergoat.domain.question;

import ch.heigvd.amt.stackovergoat.domain.Id;

import java.util.UUID;

public class QuestionId extends Id {
    public QuestionId() {
        super();
    }

    public QuestionId(String id) {
        super(id);
    }

    public QuestionId(UUID id) {
        super(id);
    }
}
