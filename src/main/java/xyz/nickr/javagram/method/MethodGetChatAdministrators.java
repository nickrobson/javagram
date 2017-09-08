package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
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
public class MethodGetChatAdministrators extends Method<ChatMember[]> {

    private Object chatId;

    public MethodGetChatAdministrators() {
        super("getChatAdministrators");
    }

    public MethodGetChatAdministrators setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodGetChatAdministrators setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodGetChatAdministrators setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        json.put("chat_id", chatId);
        return json;
    }

    @Override
    public ChatMember[] execute(TelegramBot bot) {
        JSONArray arr = (JSONArray) bot.getExecutor().exec(this);
        ChatMember[] members = new ChatMember[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            members[i] = ChatMember.from(bot, arr.getJSONObject(i));
        }
        return members;
    }

}
