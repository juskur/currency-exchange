package edu.exchange.rates;

import java.math.BigDecimal;

/**
 * Data class holding currency rate information. External data source may provide various values, so validation,
 * if needed, must be implemented by utilizing currency rate registry implementation.
 */
public class CurrencyRate {

    private String nameIso;
    private BigDecimal amount;

    /**
     * Currency Rate Constructor
     * @param nameIso ISO currency name
     * @param amount exchange rate
     */
    public CurrencyRate(String nameIso, BigDecimal amount) {
        this.nameIso = nameIso;
        this.amount = amount;
    }

    public String getNameIso() {
        return nameIso;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
