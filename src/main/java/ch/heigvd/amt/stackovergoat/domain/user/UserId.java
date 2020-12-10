package ch.heigvd.amt.stackovergoat.domain.user;

import ch.heigvd.amt.stackovergoat.domain.Id;

import java.util.UUID;

public class UserId  extends Id
{
    public UserId() {
        super();
    }

    public UserId(String id) {
        super(id);
    }

    public UserId(UUID id) {
        super(id);
    }
}
