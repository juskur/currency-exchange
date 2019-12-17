package edu.exchange.rates;

import edu.exchange.rates.impl.validators.MandatoryValueValidator;
import edu.exchange.rates.impl.validators.NotEmptyStringValueValidator;

import java.math.BigDecimal;

/**
 * Data class of calculation parameters for ExchangeAmountCalculator with validation.
 */
public class CalculationParameters {

    private Validator<BigDecimal> mandatoryValueValidator = new MandatoryValueValidator<>();
    private Validator<String> notEmptyStringValueValidator = new NotEmptyStringValueValidator();

    private String currencyNameIsoFrom;
    private String currencyNameIsoTo;
    private BigDecimal currencyAmountFrom;

    /**
     * Calculation Parameters Constructor
     * @param currencyNameIsoFrom ISO currency name From
     * @param currencyNameIsoTo ISO currency name To
     * @param currencyAmountFrom currency amount to convert
     */
    public CalculationParameters(String currencyNameIsoFrom, String currencyNameIsoTo, BigDecimal currencyAmountFrom) {
        this.currencyNameIsoFrom = currencyNameIsoFrom;
        this.currencyNameIsoTo = currencyNameIsoTo;
        this.currencyAmountFrom = currencyAmountFrom;
    }

    public void validate() throws ValidationException {
        notEmptyStringValueValidator.validate(currencyNameIsoFrom, "Currency not specified: %s", "From");
        notEmptyStringValueValidator.validate(currencyNameIsoTo, "Currency not specified: %s", "To");
        mandatoryValueValidator.validate(currencyAmountFrom, "Currency amount not specified");
    }


    public String getCurrencyNameIsoFrom() {
        return currencyNameIsoFrom;
    }

    public String getCurrencyNameIsoTo() {
        return currencyNameIsoTo;
    }

    public BigDecimal getCurrencyAmountFrom() {
        return currencyAmountFrom;
    }
}
