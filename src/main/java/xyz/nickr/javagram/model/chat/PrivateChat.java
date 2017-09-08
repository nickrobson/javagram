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
public class PrivateChat extends Chat {

    private final Optional<String> username;
    private final String firstName;
    private final Optional<String> lastName;

    public PrivateChat(TelegramBot bot, JSONObject private_chat) {
        super(bot, private_chat, ChatType.PRIVATE);
        this.username = Optional.ofNullable(private_chat.getString("username"));
        this.firstName = private_chat.getString("first_name");
        this.lastName = Optional.ofNullable(private_chat.getString("last_name"));
    }

}
