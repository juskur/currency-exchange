package edu.exchange.rates.impl;

import edu.exchange.rates.CurrencyRate;
import edu.exchange.rates.CurrencyRateRegistry;
import edu.exchange.rates.ValidationException;
import edu.exchange.rates.Validator;
import edu.exchange.rates.impl.validators.NotEmptyStringValueValidator;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * Hardcoded values implementation of registry of currency rates
 */
public class CurrencyRateRegistryHardcoded implements CurrencyRateRegistry {

    private TreeMap<String, CurrencyRate> rateRegistry = new TreeMap<>();
    private Validator<String> mandatoryValueValidator = new NotEmptyStringValueValidator();

    /**
     * Default constructor.
     */
    public CurrencyRateRegistryHardcoded() {
        super();
        loadFixedHardcodedValues();
    }

    /**
     * Load hardcoded values.
     */
    protected void loadFixedHardcodedValues() {
        putFixedCurrencyRate(new CurrencyRate("DKK", new BigDecimal(100)));
        putFixedCurrencyRate(new CurrencyRate("EUR", new BigDecimal(743.94)));
        putFixedCurrencyRate(new CurrencyRate("USD", new BigDecimal(663.11)));
        putFixedCurrencyRate(new CurrencyRate("GBP", new BigDecimal(852.85)));
        putFixedCurrencyRate(new CurrencyRate("SEK", new BigDecimal(76.10)));
        putFixedCurrencyRate(new CurrencyRate("NOK", new BigDecimal(78.40)));
        putFixedCurrencyRate(new CurrencyRate("CHF", new BigDecimal(683.58)));
        putFixedCurrencyRate(new CurrencyRate("JPY", new BigDecimal(5.9740)));
    }

    private void putFixedCurrencyRate(CurrencyRate currencyRate) {
        rateRegistry.put(currencyRate.getNameIso(), currencyRate);
    }

    /**
     * Find currency exchange rate in registry. Returns null if rate is not found
     * @param currencyNameIso ISO name of the currency, mandatory
     * @return currency rate
     * @throws ValidationException if ISO currency name is not valid
     */
    public CurrencyRate findCurrencyRateByKey(String currencyNameIso) throws ValidationException {
        validateParameters(currencyNameIso);
        return rateRegistry.get(currencyNameIso);
    }

    /**
     * Validate parameters
     * @param currencyNameIso
     * @throws ValidationException
     */
    private void validateParameters(String currencyNameIso) throws ValidationException {
        mandatoryValueValidator.validate(currencyNameIso, "Currency not specified");
    }
}
