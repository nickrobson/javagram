package xyz.nickr.javagram.model;

import lombok.Getter;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
public class Model {

    @Getter private final TelegramBot bot;

    public Model(TelegramBot bot) {
        this.bot = bot;
    }

}
