package edu.exchange.rates.impl.validators;

/**
 * Utility class to construct message.
 */
public class MessageUtils {

    public static String getValidationMessage(String errorMessage, String errorCode) {
        if (errorCode == null) {
            return errorMessage;
        } else {
            return String.format(errorMessage, errorCode);
        }
    }
}
