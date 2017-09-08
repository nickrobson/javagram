package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.user.ChatMember;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodGetChatMember extends Method<ChatMember> {

    private Object chatId;
    private long userId = -1;

    public MethodGetChatMember() {
        super("getChatMember");
    }

    public MethodGetChatMember setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodGetChatMember setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodGetChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodGetChatMember setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        json.put("chat_id", chatId);
        if (userId < 0)
            throw new InvalidMethodParameterException(this, "user_id", ">= 0");
        json.put("user_id", userId);
        return json;
    }

    @Override
    public ChatMember execute(TelegramBot bot) {
        JSONObject chat_member = (JSONObject) bot.getExecutor().exec(this);
        return ChatMember.from(bot, chat_member);
    }

}
