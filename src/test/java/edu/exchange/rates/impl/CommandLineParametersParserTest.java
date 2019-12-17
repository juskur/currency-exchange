package edu.exchange.rates.impl;

import edu.exchange.rates.CalculationParameters;
import edu.exchange.rates.ValidationException;
import edu.exchange.rates.impl.CommandLineParametersParser;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static edu.exchange.rates.impl.RoundingUtils.createWithPrecision;

/**
 * Test command line parser and validations
 */
@RunWith(JUnit4.class)
public class CommandLineParametersParserTest extends TestCase {

    CommandLineParametersParser parser = null;

    @Before
    public void initialize() throws Exception {
        this.parser = new CommandLineParametersParser();
    }

    /**
     * Test if calculation parameters can be created from valid arguments
     * @throws Exception
     */
    @Test
    public void testGetValidParameters() throws Exception {
        CalculationParameters parameters = parser.getParameters(new String[] {"EUR/DKK", "1.1"});
        assertEquals(parameters.getCurrencyNameIsoFrom(), "EUR");
        assertEquals(parameters.getCurrencyNameIsoTo(), "DKK");
        assertEquals(parameters.getCurrencyAmountFrom().doubleValue(), createWithPrecision(1.1).doubleValue());
    }

    /**
     * Test if ValidationException is raised when too few arguments are passed
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testTooLittleParameters() throws Exception {
        parser.getParameters(new String[]{"EUR/DKK"});
        fail("Passing not enough parameters does not raise any errors");
    }

    /**
     * Test if ValidationException is raised when too many arguments are passed
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testTooManyParameters() throws Exception {
        parser.getParameters(new String[]{"EUR/DKK", "EUR/DKK", "EUR/DKK"});
        fail("Passing too much parameters does not raise any errors");
    }

    /**
     * Test if ValidationException is raised when wrong separator is passed
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testWrongSeparator() throws Exception {
        parser.getParameters(new String[]{"EUR\\DKK", "1.1"});
        fail("Passing wrong separator does not raise any errors");
    }

    /**
     * Test if ValidationException is raised when amount is not a number
     * @throws Exception
     */
    @Test(expected = ValidationException.class)
    public void testAmountNotANumber() throws Exception {
        parser.getParameters(new String[] {"EUR/DKK", "1.a"});
        fail("Passing not a number does not raise any errors");
    }

    /**
     * Test that currencies are saved in uppercase for convenience
     * @throws Exception
     */
    @Test
    public void testGetValidParametersLowercase() throws Exception {
        CalculationParameters parameters = parser.getParameters(new String[] {"eur/dKk", "1.1"});
        assertEquals(parameters.getCurrencyNameIsoFrom(), "EUR");
        assertEquals(parameters.getCurrencyNameIsoTo(), "DKK");
        assertEquals(parameters.getCurrencyAmountFrom().doubleValue(), createWithPrecision(1.1).doubleValue());
    }
}
