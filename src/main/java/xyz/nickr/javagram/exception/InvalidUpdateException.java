package xyz.nickr.javagram.exception;

import org.json.JSONObject;

/**
 * @author Nick Robson
 */
public class InvalidUpdateException extends TelegramException {

    public InvalidUpdateException(String message, JSONObject json) {
        super(message + "\n\t" + json);
    }

}
