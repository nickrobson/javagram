package xyz.nickr.javagram.exception;

/**
 * @author Nick Robson
 */
public class InvalidMessageContentException extends TelegramException {

    public InvalidMessageContentException(String message) {
        super(message);
    }

}
