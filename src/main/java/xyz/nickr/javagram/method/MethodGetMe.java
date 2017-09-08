package xyz.nickr.javagram.method;

import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
public class MethodGetMe extends Method<User> {

    public MethodGetMe() {
        super("getMe");
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        return null;
    }

    @Override
    public User execute(TelegramBot bot) {
        JSONObject user = (JSONObject) bot.getExecutor().exec(this);
        return User.from(bot, user);
    }

}
