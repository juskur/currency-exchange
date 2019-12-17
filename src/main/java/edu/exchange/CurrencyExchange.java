package edu.exchange;

import edu.exchange.rates.CalculationParameters;
import edu.exchange.rates.ExchangeAmountCalculator;
import edu.exchange.rates.ValidationException;
import edu.exchange.rates.impl.CommandLineParametersParser;
import edu.exchange.rates.impl.CurrencyRateRegistryHardcoded;
import edu.exchange.rates.impl.ExchangeAmountCalculatorBasic;

/**
 * Main executable class. Pass pair of currencies and amount to convert.
 * E.g.: java edu.exchange.CurrencyExchange DKK/EUR 10
 */
public class CurrencyExchange {

    private static ExchangeAmountCalculator amountCalculator = new ExchangeAmountCalculatorBasic(new CurrencyRateRegistryHardcoded());
    private static CommandLineParametersParser parser = new CommandLineParametersParser();

    /**
     * Main entry point
     * @param args
     */
    public static void main(String[] args) {
        try {
            CalculationParameters parameters = parser.getParameters(args);
            System.out.println(amountCalculator.calculateExchangeAmount(parameters).doubleValue());
        } catch (ValidationException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(String.format("Unexpected error: %s", e.getMessage()));
        }
    }
}
