package ch.heigvd.amt.stackovergoat.application.user;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class ProposeUserCommand {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String clearTextPassword;
}
