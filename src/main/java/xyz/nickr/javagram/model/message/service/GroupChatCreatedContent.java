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
public class GroupChatCreatedContent extends ServiceMessageContent {

    public GroupChatCreatedContent(TelegramBot bot) {
        super(bot, MessageContentType.GROUP_CHAT_CREATED);
    }

}
