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
public class MethodExportChatInviteLink extends Method<String> {

    private Object chatId;

    public MethodExportChatInviteLink() {
        super("exportChatInviteLink");
    }

    public MethodExportChatInviteLink setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodExportChatInviteLink setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodExportChatInviteLink setChatId(String chatId) {
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
    public String execute(TelegramBot bot) {
        return (String) bot.getExecutor().exec(this);
    }

}
