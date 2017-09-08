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
public class MethodForwardMessage extends Method<Message> {

    private Object chatId;
    private Object fromChatId;
    private long messageId = -1;
    private Optional<Boolean> disableNotification;

    public MethodForwardMessage() {
        super("forwardMessage");
    }

    public MethodForwardMessage setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodForwardMessage setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodForwardMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodForwardMessage setFromChatId(Chat chat) {
        this.fromChatId = this.getChatId(chat);
        return this;
    }

    public MethodForwardMessage setFromChatId(long fromChatId) {
        this.fromChatId = fromChatId;
        return this;
    }

    public MethodForwardMessage setFromChatId(String fromChatId) {
        this.fromChatId = fromChatId;
        return this;
    }

    public MethodForwardMessage setMessageId(long messageId) {
        this.messageId = messageId;
        return this;
    }

    public MethodForwardMessage setDisableNotification(Boolean disableNotification) {
        this.disableNotification = Optional.ofNullable(disableNotification);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        json.put("chat_id", chatId);
        if (fromChatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        json.put("from_chat_id", fromChatId);
        if (messageId < 0)
            throw new InvalidMethodParameterException(this, "message_id", ">= 0");
        json.put("message_id", messageId);
        disableNotification.ifPresent(b -> json.put("disable_notification", b));
        return json;
    }

    @Override
    public Message execute(TelegramBot bot) {
        JSONObject message = (JSONObject) bot.getExecutor().exec(this);
        return Message.from(bot, message);
    }

}
