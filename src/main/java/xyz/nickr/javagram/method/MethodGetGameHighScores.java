package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.game.GameHighScore;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodGetGameHighScores extends Method<GameHighScore[]> {

    private long userId = -1;
    private Optional<Long> chatId = Optional.empty();
    private Optional<Long> messageId = Optional.empty();
    private Optional<String> inlineMessageId = Optional.empty();

    public MethodGetGameHighScores() {
        super("getGameHighScores");
    }

    public MethodGetGameHighScores setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodGetGameHighScores setChatMessage(Chat chat, long messageId) {
        this.chatId = Optional.ofNullable(chat.getId());
        this.messageId = Optional.ofNullable(messageId);
        this.inlineMessageId = Optional.empty();
        return this;
    }

    public MethodGetGameHighScores setChatMessage(long chatId, long messageId) {
        this.chatId = Optional.ofNullable(chatId);
        this.messageId = Optional.ofNullable(messageId);
        this.inlineMessageId = Optional.empty();
        return this;
    }

    public MethodGetGameHighScores setInlineMessage(String inlineMessageId) {
        this.chatId = Optional.empty();
        this.messageId = Optional.empty();
        this.inlineMessageId = Optional.ofNullable(inlineMessageId);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (userId < 0)
            throw new InvalidMethodParameterException(this, "user_id", ">= 0");
        json.put("user_id", userId);
        if (chatId.isPresent()) {
            if (chatId.get() < 0)
                throw new InvalidMethodParameterException(this, "chat_id", ">= 0");
            if (!messageId.isPresent())
                throw new InvalidMethodParameterException(this, "message_id");
            if (messageId.get() < 0)
                throw new InvalidMethodParameterException(this, "message_id", ">= 0");
            json.put("chat_id", chatId.get());
            json.put("message_id", messageId.get());
        } else if (inlineMessageId.isPresent()) {
            json.put("inline_message_id", inlineMessageId.get());
        } else {
            throw new InvalidMethodParameterException(this, "(chat_id AND message_id) XOR inline_message_id");
        }
        return json;
    }

    @Override
    public GameHighScore[] execute(TelegramBot bot) {
        JSONArray arr = (JSONArray) bot.getExecutor().exec(this);
        GameHighScore[] highScores = new GameHighScore[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            highScores[i] = GameHighScore.from(bot, arr.getJSONObject(i));
        }
        return highScores;
    }

}
