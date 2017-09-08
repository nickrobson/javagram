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
public class MethodPromoteChatMember extends Method<Boolean> {

    private Object chatId;
    private long userId = -1;
    private Optional<Boolean> canChangeInfo = Optional.empty();
    private Optional<Boolean> canPostMessages = Optional.empty();
    private Optional<Boolean> canEditMessages = Optional.empty();
    private Optional<Boolean> canDeleteMessages = Optional.empty();
    private Optional<Boolean> canInviteUsers = Optional.empty();
    private Optional<Boolean> canRestrictMembers = Optional.empty();
    private Optional<Boolean> canPinMessages = Optional.empty();
    private Optional<Boolean> canPromoteMembers = Optional.empty();

    public MethodPromoteChatMember() {
        super("promoteChatMember");
    }

    public MethodPromoteChatMember setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodPromoteChatMember setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodPromoteChatMember setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodPromoteChatMember setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodPromoteChatMember setCanChangeInfo(Boolean canChangeInfo) {
        this.canChangeInfo = Optional.ofNullable(canChangeInfo);
        return this;
    }

    public MethodPromoteChatMember setCanPostMessages(Boolean canPostMessages) {
        this.canPostMessages = Optional.ofNullable(canPostMessages);
        return this;
    }

    public MethodPromoteChatMember setCanEditMessages(Boolean canEditMessages) {
        this.canEditMessages = Optional.ofNullable(canEditMessages);
        return this;
    }

    public MethodPromoteChatMember setCanDeleteMessages(Boolean canDeleteMessages) {
        this.canDeleteMessages = Optional.ofNullable(canDeleteMessages);
        return this;
    }

    public MethodPromoteChatMember setCanInviteUsers(Boolean canInviteUsers) {
        this.canInviteUsers = Optional.ofNullable(canInviteUsers);
        return this;
    }

    public MethodPromoteChatMember setCanRestrictMembers(Boolean canRestrictMembers) {
        this.canRestrictMembers = Optional.ofNullable(canRestrictMembers);
        return this;
    }

    public MethodPromoteChatMember setCanPinMessages(Boolean canPinMessages) {
        this.canPinMessages = Optional.ofNullable(canPinMessages);
        return this;
    }

    public MethodPromoteChatMember setCanPromoteMembers(Boolean canPromoteMembers) {
        this.canPromoteMembers = Optional.ofNullable(canPromoteMembers);
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
        canChangeInfo.ifPresent(x -> json.put("can_change_info", x));
        canPostMessages.ifPresent(x -> json.put("can_post_messages", x));
        canEditMessages.ifPresent(x -> json.put("can_edit_messages", x));
        canDeleteMessages.ifPresent(x -> json.put("can_delete_messages", x));
        canInviteUsers.ifPresent(x -> json.put("can_invite_users", x));
        canRestrictMembers.ifPresent(x -> json.put("can_restrict_members", x));
        canPinMessages.ifPresent(x -> json.put("can_pin_messages", x));
        canPromoteMembers.ifPresent(x -> json.put("can_promote_members", x));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
