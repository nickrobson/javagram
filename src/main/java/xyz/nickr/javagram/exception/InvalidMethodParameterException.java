package xyz.nickr.javagram.exception;

import xyz.nickr.javagram.method.Method;

/**
 * @author Nick Robson
 */
public class InvalidMethodParameterException extends TelegramException {

    public InvalidMethodParameterException(Method method, String field) {
        this(method, field, "set");
    }

    public InvalidMethodParameterException(Method method, String field, String condition) {
        this(method.getRoute(), field, condition);
    }

    public InvalidMethodParameterException(String method, String field) {
        this(method, field, "set");
    }

    public InvalidMethodParameterException(String method, String field, String condition) {
        super(method + " needs `" + field + "` to be " + condition);
    }

}
