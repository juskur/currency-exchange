package edu.exchange.rates;

import java.math.BigDecimal;

/**
 * Amount calculator interface. Implementation should return calculated exchange amount.
 */
public interface ExchangeAmountCalculator {

    /**
     * Calculate Exchange Amount
     * @param parameters containing currencies and amount
     * @return calculated exchange amount
     * @throws ValidationException on invalid parameter values
     */
    BigDecimal calculateExchangeAmount(CalculationParameters parameters) throws ValidationException;

}
