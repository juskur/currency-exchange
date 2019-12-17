package edu.exchange.rates;

import edu.exchange.rates.impl.CurrencyRateRegistryHardcoded;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * Test currency rate registry if it does not allow null as a currency key
 */
@RunWith(JUnit4.class)
public class CurrencyRateRegistryTest extends TestCase {

    CurrencyRateRegistry rateRegistry = null;

    @Before
    public void initialize() throws Exception {
        this.rateRegistry = new CurrencyRateRegistryHardcoded();
    }

    /**
     * Test if expected null value is returned when not valid currency ISO name is passed.
     * @throws Exception
     */
    @Test
    public void testGetNotExistingCurrency() throws Exception {
        CurrencyRate currencyNotExisting = rateRegistry.findCurrencyRateByKey("???");
        assertNull(currencyNotExisting);
    }

    /**
     * Test if ValidationException is thrown when null currency name is passed
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testGetNullCurrency() throws Exception {
        rateRegistry.findCurrencyRateByKey(null);
        fail("findCurrencyRateByKey null does not throw an exception");
    }
}
