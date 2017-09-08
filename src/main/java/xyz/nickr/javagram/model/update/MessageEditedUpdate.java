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
public class MessageEditedUpdate extends Update {

    private final Message message;

    public MessageEditedUpdate(TelegramBot bot, long updateId, JSONObject edited_message) {
        super(bot, UpdateType.MESSAGE_EDITED, updateId);
        this.message = new Message(bot, edited_message);
    }

}
