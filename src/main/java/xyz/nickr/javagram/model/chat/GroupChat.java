package xyz.nickr.javagram.model.chat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GroupChat extends Chat {

    private final String title;
    private final boolean allMembersAreAdmins;

    public GroupChat(TelegramBot bot, JSONObject group_chat) {
        super(bot, group_chat, ChatType.GROUP);
        this.title = group_chat.getString("title");
        this.allMembersAreAdmins = group_chat.optBoolean("all_members_are_administrators");
    }

}
