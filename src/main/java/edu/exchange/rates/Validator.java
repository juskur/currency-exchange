package edu.exchange.rates;

/**
 * Validator interface. Implementations of this interface should throw ValidationException if checked
 * value is not valid
 * @param <T> Type of value being validated
 */
public interface Validator<T> {

    /**
     * Validate the value
     * @param value value
     * @param errorMessage error message with error code placeholder
     * @param errorCode error code to be added into error message
     * @throws ValidationException with validation error
     */
    void validate(T value, String errorMessage, String errorCode) throws ValidationException;

    /**
     * Validate the value
     * @param value value
     * @param errorMessage error message
     * @throws ValidationException with validation error
     */
    void validate(T value, String errorMessage) throws ValidationException;
}
