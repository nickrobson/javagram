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
public class MethodRestrictChatMember extends Method<Boolean> {

    private Object chatId;
    private long userId = -1;
    private Optional<Long> unbanDate = Optional.empty();
    private Optional<Boolean> canSendMessages = Optional.empty();
    private Optional<Boolean> canSendMediaMessages = Optional.empty();
    private Optional<Boolean> canSendOtherMessages = Optional.empty();
    private Optional<Boolean> canAddWebPagePreviews = Optional.empty();

    public MethodRestrictChatMember() {
        super("restrictChatMember");
    }

    public MethodRestrictChatMember setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodRestrictChatMember setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodRestrictChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodRestrictChatMember setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    /**
     * Sets the unix time for the unban date.
     * @param unbanDate The unban date.
     * @return This object
     */
    public MethodRestrictChatMember setUnbanDate(Long unbanDate) {
        this.unbanDate = Optional.ofNullable(unbanDate);
        return this;
    }

    public MethodRestrictChatMember setCanSendMessages(Boolean canSendMessages) {
        this.canSendMessages = Optional.ofNullable(canSendMessages);
        return this;
    }

    public MethodRestrictChatMember setCanSendMediaMessages(Boolean canSendMediaMessages) {
        this.canSendMediaMessages = Optional.ofNullable(canSendMediaMessages);
        return this;
    }

    public MethodRestrictChatMember setCanSendOtherMessages(Boolean canSendOtherMessages) {
        this.canSendOtherMessages = Optional.ofNullable(canSendOtherMessages);
        return this;
    }

    public MethodRestrictChatMember setCanAddWebPagePreviews(Boolean canAddWebPagePreviews) {
        this.canAddWebPagePreviews = Optional.ofNullable(canAddWebPagePreviews);
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
        canSendMessages.ifPresent(x -> json.put("can_send_messages", x));
        canSendMediaMessages.ifPresent(x -> json.put("can_send_media_messages", x));
        canSendOtherMessages.ifPresent(x -> json.put("can_send_other_messages", x));
        canAddWebPagePreviews.ifPresent(x -> json.put("can_add_web_page_previews", x));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
