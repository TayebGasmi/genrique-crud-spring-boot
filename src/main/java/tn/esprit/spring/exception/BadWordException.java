package tn.esprit.spring.exception;

public class BadWordException extends RuntimeException {
    public BadWordException(String message) {
        super(message);
    }
}
