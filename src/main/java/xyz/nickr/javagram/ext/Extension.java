package xyz.nickr.javagram.ext;

import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
public interface Extension {

    void register(TelegramBot bot);

}
