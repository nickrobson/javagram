package xyz.nickr.javagram.exception;


import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
public class InvalidModelParameterException extends TelegramException {

    public InvalidModelParameterException(Model model, String field) {
        this(model, field, "set");
    }

    public InvalidModelParameterException(Model model, String field, String condition) {
        this(model.getClass().getName(), field, condition);
    }

    public InvalidModelParameterException(String model, String field) {
        this(model, field, "set");
    }

    public InvalidModelParameterException(String model, String field, String condition) {
        super(model + " needs `" + field + "` to be " + condition);
    }

}
