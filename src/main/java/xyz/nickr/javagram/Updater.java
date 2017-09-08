package xyz.nickr.javagram;

/**
 * @author Nick Robson
 */
public abstract class Updater {

    private TelegramBot bot;
    private String token;

    final void init(TelegramBot bot, String token) {
        this.bot = bot;
        this.token = token;
    }

    public final TelegramBot getBot() {
        return this.bot;
    }

    public abstract void start();
    public abstract void stop();

}
