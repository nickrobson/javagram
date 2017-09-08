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
public class MethodUnbanChatMember extends Method<Boolean> {

    private Object chatId;
    private long userId = -1;

    public MethodUnbanChatMember() {
        super("unbanChatMember");
    }

    public MethodUnbanChatMember setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodUnbanChatMember setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodUnbanChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodUnbanChatMember setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (userId < 0)
            throw new InvalidMethodParameterException(this, "user_id", ">= 0");
        json.put("chat_id", chatId);
        json.put("user_id", userId);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
