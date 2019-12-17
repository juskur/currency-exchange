package edu.exchange.rates.impl;

import edu.exchange.rates.*;
import static edu.exchange.rates.impl.RoundingUtils.*;

import edu.exchange.rates.impl.validators.MandatoryValueValidator;
import edu.exchange.rates.impl.validators.PositiveNumberValueValidator;

import java.math.BigDecimal;

/**
 * Basic implementation of amount calculator.
 */
public class ExchangeAmountCalculatorBasic implements ExchangeAmountCalculator {

    private CurrencyRateRegistry rateRegistry;
    private Validator mandatoryValueValidator = new MandatoryValueValidator<>();
    private Validator<BigDecimal> positiveNumberValueValidator = new PositiveNumberValueValidator();

    /**
     * Constructor of Calculator.
     * @param rateRegistry rate registry implementation
     */
    public ExchangeAmountCalculatorBasic(CurrencyRateRegistry rateRegistry) {
        this.rateRegistry = rateRegistry;
    }

    /**
     * Validate all parameters and values and calculate exchange amount.
     * @param parameters containing currencies and amount
     * @return amount of exchange
     * @throws ValidationException on any parameter or calculation value validation error.
     */
    public BigDecimal calculateExchangeAmount(CalculationParameters parameters) throws ValidationException {
        validateParameters(parameters);
        CurrencyRate rateFrom = rateRegistry.findCurrencyRateByKey(parameters.getCurrencyNameIsoFrom());
        CurrencyRate rateTo = rateRegistry.findCurrencyRateByKey(parameters.getCurrencyNameIsoTo());
        validateValues(rateFrom, rateTo, parameters.getCurrencyAmountFrom());
        return doAmountCalculationValidated(rateFrom.getAmount(), rateTo.getAmount(), parameters.getCurrencyAmountFrom());
    }

    /**
     * Do the calculation
     * @param rateFrom exchange rate from
     * @param rateTo exchange rate to
     * @param currencyAmountFrom currency amount from
     * @return
     */
    private BigDecimal doAmountCalculationValidated(BigDecimal rateFrom, BigDecimal rateTo, BigDecimal currencyAmountFrom) {
        return roundToPrecision(divideWithPrecision(rateFrom, rateTo).multiply(currencyAmountFrom));
    }

    /**
     * Validate parameters
     * @param parameters parameters to be validated
     * @throws ValidationException on validation error
     */
    private void validateParameters(CalculationParameters parameters) throws ValidationException {
        mandatoryValueValidator.validate(parameters, "Parameters are not provided");
        parameters.validate();
    }

    /**
     * Validate values before calling actual mathematical calculation
     * @param rateFrom currency rate from
     * @param rateTo currency rate to
     * @param currencyAmountFrom currency amount from
     * @throws ValidationException on validation error
     */
    private void validateValues(CurrencyRate rateFrom, CurrencyRate rateTo, BigDecimal currencyAmountFrom) throws ValidationException {
        mandatoryValueValidator.validate(rateFrom, "Rate not found: %s", "From");
        mandatoryValueValidator.validate(rateTo, "Rate not found: %s", "To");
        positiveNumberValueValidator.validate(rateFrom.getAmount(), "Rate from should be not empty and greater than zero: %s", String.valueOf(rateFrom.getAmount()));
        positiveNumberValueValidator.validate(rateTo.getAmount(), "Rate to should be not empty and greater than zero: %s", String.valueOf(rateTo.getAmount()));
        positiveNumberValueValidator.validate(currencyAmountFrom, "Currency amount should be not empty and greater than zero: %s", String.valueOf(currencyAmountFrom));
    }
}
