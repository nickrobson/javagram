package xyz.nickr.javagram.method;

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
public class MethodDeleteMessage extends Method<Boolean> {

    private Object chatId;
    private long messageId = -1;

    public MethodDeleteMessage() {
        super("deleteMessage");
    }

    public MethodDeleteMessage setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodDeleteMessage setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodDeleteMessage setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodDeleteMessage setMessageId(long messageId) {
        this.messageId = messageId;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        json.put("chat_id", chatId);
        if (messageId < 0)
            throw new InvalidMethodParameterException(this, "message_id", ">= 0");
        json.put("message_id", messageId);
        return json;
    }
    
    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
