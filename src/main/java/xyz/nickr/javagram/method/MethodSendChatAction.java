package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.ChatAction;
import xyz.nickr.javagram.model.chat.Chat;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodSendChatAction extends Method {

    private Object chatId;
    private ChatAction action;

    public MethodSendChatAction() {
        super("sendChatAction");
    }

    public MethodSendChatAction setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodSendChatAction setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSendChatAction setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSendChatAction setChatAction(ChatAction action) {
        this.action = action;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (action == null)
            throw new InvalidMethodParameterException(this, "action");
        json.put("chat_id", chatId);
        json.put("action", action.toString().toLowerCase());
        return json;
    }

    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
