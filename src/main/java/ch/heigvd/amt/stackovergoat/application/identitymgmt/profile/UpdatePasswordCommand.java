package ch.heigvd.amt.stackovergoat.application.identitymgmt.profile;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class UpdatePasswordCommand {
     private String username;
     private String newClearTextPassword;
}
