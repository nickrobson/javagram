package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.replymarkup.InlineKeyboardMarkup;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendGame extends Method {

    private long chatId = -1;
    private String gameShortName;
    private Optional<Boolean> disableNotification = Optional.empty();
    private Optional<Long> replyToMessageId = Optional.empty();
    private Optional<InlineKeyboardMarkup> replyMarkup = Optional.empty();

    public MethodSendGame() {
        super("sendGame");
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId < 0)
            throw new InvalidMethodParameterException(this, "chat_id", ">= 0");
        json.put("chat_id", chatId);
        if (gameShortName == null)
            throw new InvalidMethodParameterException(this, "game_short_name");
        json.put("game_short_name", gameShortName);
        disableNotification.ifPresent(b -> json.put("disable_notification", b));
        replyToMessageId.ifPresent(i -> json.put("reply_to_message_id", i));
        replyMarkup.ifPresent(m -> json.put("reply_markup", m.toJSON()));
        return json;
    }

    public Message execute(TelegramBot bot) {
        JSONObject message = (JSONObject) bot.getExecutor().exec(this);
        return Message.from(bot, message);
    }

}
