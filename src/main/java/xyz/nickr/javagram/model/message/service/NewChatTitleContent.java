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
public class NewChatTitleContent extends ServiceMessageContent {

    private final String title;

    public NewChatTitleContent(TelegramBot bot, String new_chat_title) {
        super(bot, MessageContentType.NEW_CHAT_TITLE);
        this.title = new_chat_title;
    }

}
