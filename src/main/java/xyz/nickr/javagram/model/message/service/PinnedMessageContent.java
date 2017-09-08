package xyz.nickr.javagram.model.message.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.message.MessageContentType;
import xyz.nickr.javagram.model.message.ServiceMessageContent;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PinnedMessageContent extends ServiceMessageContent {

    private final Message message;

    public PinnedMessageContent(TelegramBot bot, JSONObject pinned_message) {
        super(bot, MessageContentType.PINNED_MESSAGE);
        this.message = new Message(bot, pinned_message);
    }

}
