package ch.heigvd.amt.stackovergoat.infrastructure.persistence.exception;

public class BusinessException extends Exception {
    public BusinessException(String message) {
        super(message);
    }
}