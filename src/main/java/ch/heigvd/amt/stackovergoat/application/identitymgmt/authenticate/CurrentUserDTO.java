package ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CurrentUserDTO {
    private String id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
}
