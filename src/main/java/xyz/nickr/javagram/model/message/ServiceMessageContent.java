package xyz.nickr.javagram.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceMessageContent extends MessageContent {

    public ServiceMessageContent(TelegramBot bot, MessageContentType type) {
        super(bot, type);
    }

}
