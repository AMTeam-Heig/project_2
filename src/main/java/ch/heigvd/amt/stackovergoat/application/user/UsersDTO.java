package ch.heigvd.amt.stackovergoat.application.user;

import ch.heigvd.amt.stackovergoat.domain.user.UserId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Singular;

import java.util.List;

@Builder
@Getter
@EqualsAndHashCode
public class UsersDTO {

    @Builder
    @Getter
    @EqualsAndHashCode
    public static class UserDTO {
        private UserId id;
        private String firstname;
        private String lastname;
        private String username;
    }

    @Singular
    private List<UserDTO> users;
}
