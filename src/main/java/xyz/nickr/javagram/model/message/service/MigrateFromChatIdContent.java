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
public class MigrateFromChatIdContent extends ServiceMessageContent {

    private final long oldChatId;

    public MigrateFromChatIdContent(TelegramBot bot, long migrate_from_chat_id) {
        super(bot, MessageContentType.MIGRATE_FROM_CHAT_ID);
        this.oldChatId = migrate_from_chat_id;
    }

}
