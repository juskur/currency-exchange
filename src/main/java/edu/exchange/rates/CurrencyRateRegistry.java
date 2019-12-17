package edu.exchange.rates;

/**
 * Interface to access the registry of configured currency exchange rates
 */
public interface CurrencyRateRegistry {

    /**
     * Find currency exchange rate by the currency ISO name
     * @param currencyNameIso ISO name of the currency
     * @return exchange rate
     * @throws ValidationException if currency provided is not valid or not configured
     */
    CurrencyRate findCurrencyRateByKey(String currencyNameIso) throws ValidationException;

}
