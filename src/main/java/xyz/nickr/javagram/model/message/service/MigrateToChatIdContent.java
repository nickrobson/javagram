package xyz.nickr.javagram.model.message.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContentType;
import xyz.nickr.javagram.model.message.ServiceMessageContent;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MigrateToChatIdContent extends ServiceMessageContent {

    private final long newChatId;

    public MigrateToChatIdContent(TelegramBot bot, long migrate_to_chat_id) {
        super(bot, MessageContentType.MIGRATE_TO_CHAT_ID);
        this.newChatId = migrate_to_chat_id;
    }

}
