package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodPinChatMessage extends Method<Boolean> {

    private Object chatId;
    private long messageId = -1;
    private Optional<Boolean> disableNotification = Optional.empty();

    public MethodPinChatMessage() {
        super("setChatTitle");
    }

    public MethodPinChatMessage setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodPinChatMessage setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodPinChatMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodPinChatMessage setMessageId(long messageId) {
        this.messageId = messageId;
        return this;
    }

    public MethodPinChatMessage setMessageId(Boolean disableNotification) {
        this.disableNotification = Optional.ofNullable(disableNotification);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (messageId < 0)
            throw new InvalidMethodParameterException(this, "message_id", ">= 0");
        json.put("chat_id", chatId);
        json.put("message_id", messageId);
        disableNotification.ifPresent(x -> json.put("disable_notification", x));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
