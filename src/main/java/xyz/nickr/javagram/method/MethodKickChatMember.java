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
public class MethodKickChatMember extends Method<Boolean> {

    private Object chatId;
    private long userId = -1;
    private Optional<Long> unbanDate = Optional.empty();

    public MethodKickChatMember() {
        super("kickChatMember");
    }

    public MethodKickChatMember setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodKickChatMember setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodKickChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodKickChatMember setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Sets the unix time for the unban date.
     * @param unbanDate The unban date.
     * @return This object
     */
    public MethodKickChatMember setUnbanDate(Long unbanDate) {
        this.unbanDate = Optional.ofNullable(unbanDate);
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
        unbanDate.ifPresent(x -> json.put("until_date", x));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
