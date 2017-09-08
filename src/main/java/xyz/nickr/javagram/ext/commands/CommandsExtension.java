package xyz.nickr.javagram.ext.commands;

import lombok.Getter;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.ext.Extension;

/**
 * @author Nick Robson
 */
public class CommandsExtension implements Extension {

    TelegramBot bot;
    CommandsExtensionListener listener;
    @Getter CommandsExtensionDispatcher dispatcher;

    private boolean registered;

    public CommandsExtension() {
        this.registered = false;
        this.dispatcher = new CommandsExtensionDispatcher();
        this.listener = new CommandsExtensionListener(this);
    }

    @Override
    public void register(TelegramBot bot) {
        if (registered & (registered = true))
            return;
        this.bot = bot;
        bot.getDispatcher().register(listener);
    }

}
