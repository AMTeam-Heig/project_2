package ch.heigvd.amt.stackovergoat.application.identitymgmt.login;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class RegisterCommand {
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String clearTextPassword;
}
