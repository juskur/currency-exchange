package edu.exchange.rates.impl;

import edu.exchange.rates.CalculationParameters;
import edu.exchange.rates.ValidationException;

import java.math.BigDecimal;
import java.util.StringTokenizer;

/**
 * Parser of command line parameters. Constructs CalculationParameters
 */
public class CommandLineParametersParser {

    public static final String CURRENCIES_SEPARATOR = "/";

    /**
     * Get validated parameters from array of command line arguments
     * @param args array of command line parameters
     * @return calculation parameters
     * @throws ValidationException if command line parameters are not specified or not in expected format
     */
    public CalculationParameters getParameters(String[] args) throws ValidationException {
        validateParametersCount(args);
        return createParametersFromCommandLineArguments(args);
    }

    /**
     * Create instance of validated parameters
     * @param args arguments
     * @return validated parameters
     * @throws ValidationException on validation error
     */
    private CalculationParameters createParametersFromCommandLineArguments(String[] args) throws ValidationException {
        StringTokenizer tokenizer = new StringTokenizer(args[0], CURRENCIES_SEPARATOR);
        validateCurrencyTokensCount(tokenizer);
        return new CalculationParameters(tokenizer.nextToken().toUpperCase(), tokenizer.nextToken().toUpperCase(), parseAmount(args[1]));
    }

    /**
     * Parse and validate amount argument
     * @param amountArgument amount argument as string
     * @return parsed number
     * @throws ValidationException if value is not a number
     */
    private BigDecimal parseAmount(String amountArgument) throws ValidationException {
        BigDecimal amount = null;
        try {
            amount = new BigDecimal(amountArgument);
        } catch (NumberFormatException e) {
            throw new ValidationException("Error: Amount is not a number. E.g.: java edu.exchange.CurrencyExchange EUR/DKK 1");
        }
        return amount;
    }

    /**
     * Validate count of currencies provided with separator
     * @param tokenizer
     * @throws ValidationException
     */
    private void validateCurrencyTokensCount(StringTokenizer tokenizer) throws ValidationException {
        if (tokenizer.countTokens() != 2) {
            throw new ValidationException("Error: Wrong number of currencies. E.g.: java edu.exchange.CurrencyExchange EUR/DKK 1");
        }
    }

    /**
     * Validate count of parameters
     * @param args
     * @throws ValidationException
     */
    private void validateParametersCount(String[] args) throws ValidationException {
        if (args == null || args.length != 2) {
            throw new ValidationException("Error: Wrong number of parameters. E.g.: java edu.exchange.CurrencyExchange EUR/DKK 1");
        }
    }
}
