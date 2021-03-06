package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.Message;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ChannelPostUpdate extends Update {

    private final Message message;

    public ChannelPostUpdate(TelegramBot bot, long updateId, JSONObject channel_post) {
        super(bot, UpdateType.CHANNEL_POST, updateId);
        this.message = Message.from(bot, channel_post);
    }

}
