package ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception;

import ch.heigvd.amt.stackovergoat.application.BusinessException;

public class IntegrityConstraintViolationException extends BusinessException {
    public IntegrityConstraintViolationException(String message) {
        super(message);
    }
}
