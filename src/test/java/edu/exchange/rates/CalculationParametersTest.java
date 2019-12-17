package edu.exchange.rates;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

/**
 * Test validation of Calculation Parameters data class
 */
@RunWith(JUnit4.class)
public class CalculationParametersTest extends TestCase {

    /**
     * Test if parameters can be created with correct values. Positive test
     * @throws Exception
     */
    @Test
    public void testParametersValid() throws Exception {
        CalculationParameters parameters = new CalculationParameters("DKK", "USD", BigDecimal.ONE);
        parameters.validate();
        assertEquals(parameters.getCurrencyNameIsoFrom(), "DKK");
        assertEquals(parameters.getCurrencyNameIsoTo(), "USD");
        assertEquals(parameters.getCurrencyAmountFrom(), BigDecimal.ONE);
    }

    /**
     * Check that ValidationException is raised if currency name From is null
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterFromNull() throws Exception {
        CalculationParameters parameters = new CalculationParameters(null, "DKK", BigDecimal.ONE);
        parameters.validate();
        fail("Null currency name From does not raise any exceptions");
    }

    /**
     * Check that ValidationException is raised if currency name To is null
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterToNull() throws Exception {
        CalculationParameters parameters = new CalculationParameters("DKK", null, BigDecimal.ONE);
        parameters.validate();
        fail("Null currency name To does not raise any exceptions");
    }

    /**
     * Check that ValidationException is raised if currency name From is empty
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterFromEmpty() throws Exception {
        CalculationParameters parameters = new CalculationParameters("", "DKK", BigDecimal.ONE);
        parameters.validate();
        fail("Empty currency name From does not raise any exceptions");
    }

    /**
     * Check that ValidationException is raised if currency name To is empty
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterToEmpty() throws Exception {
        CalculationParameters parameters = new CalculationParameters("DKK", "   ", BigDecimal.ONE);
        parameters.validate();
        fail("Empty currency name To does not raise any exceptions");
    }

    /**
     * Check that ValidationException is raised when currency amount is null
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterAmountEmpty() throws Exception {
        CalculationParameters parameters = new CalculationParameters("DKK", "DKK", null);
        parameters.validate();
        fail("Null Amount does not raise any exceptions");
    }
}
