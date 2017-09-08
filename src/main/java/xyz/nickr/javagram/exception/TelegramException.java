package xyz.nickr.javagram.exception;

/**
 * @author Nick Robson
 */
public class TelegramException extends RuntimeException {

    public TelegramException() {}

    public TelegramException(String message) {
        super(message);
    }

    public TelegramException(String message, Throwable cause) {
        super(message, cause);
    }

    public TelegramException(Throwable cause) {
        super(cause);
    }

}
