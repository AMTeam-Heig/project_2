package ch.heigvd.amt.stackovergoat.application.identitymgmt.authenticate;

import ch.heigvd.amt.stackovergoat.application.BusinessException;

public class AuthentificationFailedException extends BusinessException {
    public AuthentificationFailedException(String message) {
        super(message);
    }
}
