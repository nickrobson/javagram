package xyz.nickr.javagram.model;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.payment.ShippingAddress;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class CallbackQuery extends Model {

    private final String id;
    private final User user;
    private final Optional<Message> message;
    private final Optional<String> inlineMessageId;
    private final String chatInstance;
    private final Optional<String> data;
    private final Optional<String> gameShortName;

    public CallbackQuery(TelegramBot bot, JSONObject callback_query) {
        super(bot);
        this.id = callback_query.getString("id");
        this.user = User.from(bot, callback_query.getJSONObject("from"));
        this.message = Optional.ofNullable(Message.from(bot, callback_query.optJSONObject("message")));
        this.inlineMessageId = Optional.ofNullable(callback_query.optString("inline_message_id"));
        this.chatInstance = callback_query.getString("chat_instance");
        this.data = Optional.ofNullable(callback_query.optString("data"));
        this.gameShortName = Optional.ofNullable(callback_query.optString("game_short_name"));
    }

    public static CallbackQuery from(TelegramBot bot, JSONObject callback_query) {
        return callback_query != null
                ? new CallbackQuery(bot, callback_query)
                : null;
    }

}
