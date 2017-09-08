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
public class ChannelChat extends Chat {

    private final String title;
    private final Optional<String> username;

    public ChannelChat(TelegramBot bot, JSONObject channel_chat) {
        super(bot, channel_chat, ChatType.CHANNEL);
        this.title = channel_chat.getString("title");
        this.username = Optional.ofNullable(channel_chat.getString("username"));
    }

}
