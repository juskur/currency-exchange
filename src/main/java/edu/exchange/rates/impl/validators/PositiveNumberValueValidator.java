package edu.exchange.rates.impl.validators;

import edu.exchange.rates.ValidationException;
import edu.exchange.rates.Validator;

import java.math.BigDecimal;

/**
 * Positive number validator. Checks that number is not null and is positive.
 */
public class PositiveNumberValueValidator extends MandatoryValueValidator<BigDecimal> {

    @Override
    public void validate(BigDecimal value, String errorMessage, String errorCode) throws ValidationException {
        super.validate(value, errorMessage, errorCode);
        if (value.compareTo(BigDecimal.ZERO) < 1) {
            throw new ValidationException(MessageUtils.getValidationMessage(errorMessage, errorCode));
        }
    }
}
