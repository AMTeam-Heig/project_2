package ch.heigvd.amt.stackovergoat.application.identitymgmt.login;

import ch.heigvd.amt.stackovergoat.application.BusinessException;
import lombok.Value;

@Value
public class RegistrationFailedException extends BusinessException {
    public RegistrationFailedException(String message) {
        super(message);
    }
}
