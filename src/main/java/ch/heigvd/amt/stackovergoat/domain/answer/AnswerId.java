package ch.heigvd.amt.stackovergoat.domain.answer;

import ch.heigvd.amt.stackovergoat.domain.Id;

import java.util.UUID;

public class AnswerId  extends Id {
    public AnswerId() {
        super();
    }

    public AnswerId(String id) {
        super(id);
    }

    public AnswerId(UUID id) {
        super(id);
    }
}
