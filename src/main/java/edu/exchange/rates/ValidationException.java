package edu.exchange.rates;

/**
 * Validation exception used to separate validation errors.
 */
public class ValidationException extends Exception {

    /**
     * Validation error constructor.
     * @param message validation error message
     */
    public ValidationException(String message) {
        super(message);
    }
}
