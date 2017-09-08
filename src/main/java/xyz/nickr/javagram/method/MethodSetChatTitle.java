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
public class MethodSetChatTitle extends Method<Boolean> {

    private Object chatId;
    private String title;

    public MethodSetChatTitle() {
        super("setChatTitle");
    }

    public MethodSetChatTitle setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodSetChatTitle setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatTitle setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatTitle setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (title == null)
            throw new InvalidMethodParameterException(this, "title");
        json.put("chat_id", chatId);
        json.put("title", title);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
