package ch.heigvd.amt.stackovergoat.application.identitymgmt.profile;

import ch.heigvd.amt.stackovergoat.application.BusinessException;

public class UpdateFailedException extends BusinessException {
    public UpdateFailedException(String message) {
        super(message);
    }
}
