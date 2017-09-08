package xyz.nickr.javagram.model.message.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
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
public class NewChatMembersContent extends ServiceMessageContent {

    private final User[] users;

    public NewChatMembersContent(TelegramBot bot, JSONArray new_chat_members) {
        super(bot, MessageContentType.NEW_CHAT_MEMBERS);
        this.users = new User[new_chat_members.length()];
        for (int i = 0; i < this.users.length; i++) {
            this.users[i] = new User(bot, new_chat_members.getJSONObject(i));
        }
    }

}
