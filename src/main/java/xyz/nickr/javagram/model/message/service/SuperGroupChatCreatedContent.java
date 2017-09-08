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
public class SuperGroupChatCreatedContent extends ServiceMessageContent {

    public SuperGroupChatCreatedContent(TelegramBot bot) {
        super(bot, MessageContentType.SUPERGROUP_CHAT_CREATED);
    }

}
