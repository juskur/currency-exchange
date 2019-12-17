package edu.exchange.rates.impl.validators;

import edu.exchange.rates.ValidationException;

/**
 * Not empty string validator. Checks if string is not null and not empty
 */
public class NotEmptyStringValueValidator extends MandatoryValueValidator<String> {
    @Override
    public void validate(String value, String errorMessage, String errorCode) throws ValidationException {
        super.validate(value, errorMessage, errorCode);
        if ("".equals(value.trim())) {
            throw new ValidationException(MessageUtils.getValidationMessage(errorMessage, errorCode));
        }
    }
}
