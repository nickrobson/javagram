package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.message.Message;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSetGameScore extends Method<Message> {

    private long userId = -1;
    private int score = -1;
    private Optional<Boolean> force = Optional.empty();
    private Optional<Boolean> disableEditMessage = Optional.empty();
    private Optional<Long> chatId = Optional.empty();
    private Optional<Long> messageId = Optional.empty();
    private Optional<String> inlineMessageId = Optional.empty();

    public MethodSetGameScore() {
        super("setGameScore");
    }

    public MethodSetGameScore setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodSetGameScore setScore(int score) {
        this.score = score;
        return this;
    }

    public MethodSetGameScore setForceUpdate(Boolean force) {
        this.force = Optional.ofNullable(force);
        return this;
    }

    public MethodSetGameScore setDisableEditMessage(Boolean disableEditMessage) {
        this.disableEditMessage = Optional.ofNullable(disableEditMessage);
        return this;
    }

    public MethodSetGameScore setChatMessage(Chat chat, long messageId) {
        this.chatId = Optional.ofNullable(chat.getId());
        this.messageId = Optional.ofNullable(messageId);
        this.inlineMessageId = Optional.empty();
        return this;
    }

    public MethodSetGameScore setChatMessage(long chatId, long messageId) {
        this.chatId = Optional.ofNullable(chatId);
        this.messageId = Optional.ofNullable(messageId);
        this.inlineMessageId = Optional.empty();
        return this;
    }

    public MethodSetGameScore setInlineMessage(String inlineMessageId) {
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
        json.put("score", score);
        force.ifPresent(b -> json.put("force", b));
        disableEditMessage.ifPresent(b -> json.put("disable_edit_message", b));
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
    public Message execute(TelegramBot bot) {
        Object object = bot.getExecutor().exec(this);
        if (object instanceof Boolean)
            return null;
        JSONObject message = (JSONObject) object;
        return Message.from(bot, message);
    }

}
