package edu.exchange.rates.impl.validators;

import edu.exchange.rates.ValidationException;
import edu.exchange.rates.Validator;

/**
 * Mandatory value validator
 * @param <T> type of the value
 */
public class MandatoryValueValidator<T> implements Validator<T> {

    @Override
    public void validate(T value, String errorMessage, String errorCode) throws ValidationException {
        if (value == null) {
            throw new ValidationException(MessageUtils.getValidationMessage(errorMessage, errorCode));
        }
    }

    @Override
    public void validate(T value, String errorMessage) throws ValidationException {
        this.validate(value, errorMessage, null);
    }
}
