package nz.co.rroques.web.exception;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

    private final Errors errors;

    public ValidationException(Errors errors) {
        this.errors = errors;
    }

    public Errors getErrors() {
        return errors;
    }
}
