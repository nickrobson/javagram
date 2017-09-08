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
public class MethodSetChatDescription extends Method<Boolean> {

    private Object chatId;
    private String description;

    public MethodSetChatDescription() {
        super("setChatDescription");
    }

    public MethodSetChatDescription setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodSetChatDescription setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatDescription setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatDescription setDescription(String description) {
        this.description = description;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (description == null)
            throw new InvalidMethodParameterException(this, "description");
        json.put("chat_id", chatId);
        json.put("description", description);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
