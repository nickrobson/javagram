package xyz.nickr.javagram.model.message.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContentType;
import xyz.nickr.javagram.model.message.ServiceMessageContent;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewChatMemberContent extends ServiceMessageContent {

    private final User user;

    public NewChatMemberContent(TelegramBot bot, JSONObject new_chat_member) {
        super(bot, MessageContentType.NEW_CHAT_MEMBER);
        this.user = new User(bot, new_chat_member);
    }

}
