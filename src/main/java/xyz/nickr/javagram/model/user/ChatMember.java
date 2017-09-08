package xyz.nickr.javagram.model.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ChatMember extends User {

    private final ChatMemberStatus status;

    public ChatMember(TelegramBot bot, JSONObject chat_member) {
        super(bot, chat_member.getJSONObject("user"));
        this.status = ChatMemberStatus.valueOf(chat_member.getString("status").toUpperCase());
    }

    public static ChatMember from(TelegramBot bot, JSONObject chat_member) {
        return chat_member != null
                ? new ChatMember(bot, chat_member)
                : null;
    }

}
