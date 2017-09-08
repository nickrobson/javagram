package xyz.nickr.javagram.ext.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nick Robson
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Command {

    /**
     * The aliases that may be used to invoke this command.
     *
     * @return The aliases.
     */
    String[] value();

    /**
     * Whether or not to also receive channel post notifications.
     *
     * @return True iff receiving channel posts is wanted.
     */
    boolean channelPosts() default false;

}
