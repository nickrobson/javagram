package xyz.nickr.javagram.model.chat;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SuperGroupChat extends Chat {

    private final String title;
    private final Optional<String> username;

    public SuperGroupChat(TelegramBot bot, JSONObject supergroup_chat) {
        super(bot, supergroup_chat, ChatType.SUPERGROUP);
        this.title = supergroup_chat.getString("title");
        this.username = Optional.ofNullable(supergroup_chat.getString("username"));
    }

}
