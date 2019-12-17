package edu.exchange.rates.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Rounding utility class
 */
public class RoundingUtils {
    public static final int ROUNDING_PRECISION = 4;

    /**
     * Round to precision
     * @param value value to round
     * @return rounded value
     */
    public static BigDecimal roundToPrecision(BigDecimal value) {
        return value.setScale(ROUNDING_PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Create value with precision
     * @param value value to create from
     * @return rounded value
     */
    public static BigDecimal createWithPrecision(double value) {
        return new BigDecimal(value).setScale(ROUNDING_PRECISION, RoundingMode.HALF_UP);
    }

    /**
     * Divide values with precision
     * @param value value to divide
     * @param divisor divisor
     * @return divided with precision result
     */
    public static BigDecimal divideWithPrecision(BigDecimal value, BigDecimal divisor) {
        return value.divide(divisor, ROUNDING_PRECISION, RoundingMode.HALF_UP);
    }
}
