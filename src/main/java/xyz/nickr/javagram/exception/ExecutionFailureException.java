package xyz.nickr.javagram.exception;

import java.util.Optional;
import lombok.Getter;
import org.json.JSONObject;

/**
 * @author Nick Robson
 */
@Getter
public class ExecutionFailureException extends TelegramException {

    private final Optional<String> description;
    private final Optional<Integer> errorCode;
    private final Optional<JSONObject> parameters;

    public ExecutionFailureException(String description, int errorCode, JSONObject parameters) {
        super(String.format("Error %d: %s", errorCode, description));
        this.description = Optional.ofNullable(description);
        this.errorCode = Optional.ofNullable(errorCode);
        this.parameters = Optional.ofNullable(parameters);
    }

    public ExecutionFailureException(String message) {
        super(message);
        this.description = Optional.empty();
        this.errorCode = Optional.empty();
        this.parameters = Optional.empty();
    }

    public ExecutionFailureException(String message, Throwable cause) {
        super(message, cause);
        this.description = Optional.empty();
        this.errorCode = Optional.empty();
        this.parameters = Optional.empty();
    }

}
