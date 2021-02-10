package ua.com.verdysh.payments.exceptions;

public class ValidationException extends RuntimeException {

    private String message;

    public ValidationException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}