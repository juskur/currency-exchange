package edu.exchange.rates;

import edu.exchange.rates.impl.ExchangeAmountCalculatorBasic;
import static edu.exchange.rates.impl.RoundingUtils.*;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

@RunWith(JUnit4.class)
public class ExchangeAmountCalculatorTest extends TestCase {

    /**
     * Test if ValidationException is raised when null parameters are provided
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParametersNotProvided() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        calculator.calculateExchangeAmount(null);
        fail("Null parameters does not raise any exceptions");
    }

    /**
     * Test if ValidationException is raised when currency name From is not found
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterFromDoesNotExist() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("DKK")).thenReturn(new CurrencyRate("DKK", new BigDecimal(100)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        calculator.calculateExchangeAmount(new CalculationParameters("???", "DKK", BigDecimal.ONE));
        fail("Not existing currency name From does not raise any exceptions");
    }

    /**
     * Test if ValidationException is raised when currency name To is not found
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterToDoesNotExist() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("DKK")).thenReturn(new CurrencyRate("DKK", new BigDecimal(100)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        calculator.calculateExchangeAmount(new CalculationParameters("DKK", "???", BigDecimal.ONE));
        fail("Not existing currency name To does not raise any exceptions");
    }

    /**
     * Test if ValidationException is raised when currency amount from is negative number
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testParameterAmountNegative() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("DKK")).thenReturn(new CurrencyRate("DKK", new BigDecimal(100)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        calculator.calculateExchangeAmount(new CalculationParameters("DKK", "DKK", new BigDecimal(-1)));
        fail("Negative currency amount does not raise any exceptions");
    }

    /**
     * Test if correct exchange amount is calculated for EUR to DKK conversion as in task description
     * @throws Exception
     */
    @Test
    public void testEURToDKK1() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("DKK")).thenReturn(new CurrencyRate("DKK", new BigDecimal(100)));
        when(rateRegistry.findCurrencyRateByKey("EUR")).thenReturn(new CurrencyRate("EUR", new BigDecimal(743.94)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        BigDecimal exchangeAmount = calculator.calculateExchangeAmount(new CalculationParameters("EUR", "DKK", BigDecimal.ONE));
        assertEquals(exchangeAmount.doubleValue(), createWithPrecision(7.4394).doubleValue());
    }

    /**
     * Test if the same amount is returned when currency names match
     * @throws Exception
     */
    @Test
    public void testDKKtoDKK() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("DKK")).thenReturn(new CurrencyRate("DKK", new BigDecimal(100)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        BigDecimal exchangeAmount = calculator.calculateExchangeAmount(new CalculationParameters("DKK", "DKK", new BigDecimal(1000.99)));
        assertEquals(exchangeAmount.doubleValue(), createWithPrecision(1000.99).doubleValue());
    }

    /**
     * Test if correct exchange amount is calculated other pair of currencies
     * @throws Exception
     */
    @Test
    public void testUSDToGBP() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("USD")).thenReturn(new CurrencyRate("USD", new BigDecimal(663.11)));
        when(rateRegistry.findCurrencyRateByKey("GBP")).thenReturn(new CurrencyRate("GBP", new BigDecimal(852.85)));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        BigDecimal exchangeAmount = calculator.calculateExchangeAmount(new CalculationParameters("USD", "GBP", new BigDecimal(663.11)));
        assertEquals(exchangeAmount.doubleValue(), createWithPrecision(515.5680).doubleValue());
    }

    /**
     * Test if ValidationException is raised to prevent division from zero.
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testDivisionByZero() throws Exception {
        CurrencyRateRegistry rateRegistry = mock(CurrencyRateRegistry.class);
        when(rateRegistry.findCurrencyRateByKey("USD")).thenReturn(new CurrencyRate("USD", new BigDecimal(663.11)));
        when(rateRegistry.findCurrencyRateByKey("GBP")).thenReturn(new CurrencyRate("GBP", BigDecimal.ZERO));
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistry);
        calculator.calculateExchangeAmount(new CalculationParameters("USD", "GBP", new BigDecimal(663.11)));
        fail("Division by 0 have not raised any exceptions");
    }

    /**
     * Test if ValidationException is raised when registry returns null values for valid currency names.
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testNullReturningRegistry() throws Exception {
        CurrencyRateRegistry rateRegistryReturningNulls = mock(CurrencyRateRegistry.class);
        ExchangeAmountCalculator calculator = new ExchangeAmountCalculatorBasic(rateRegistryReturningNulls);
        calculator.calculateExchangeAmount(new CalculationParameters("EUR", "DKK", BigDecimal.ONE));
        fail("Calculation on null returning registry should throw validation error");
    }
}
