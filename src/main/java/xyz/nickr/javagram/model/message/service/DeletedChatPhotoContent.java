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
public class DeletedChatPhotoContent extends ServiceMessageContent {

    public DeletedChatPhotoContent(TelegramBot bot) {
        super(bot, MessageContentType.DELETE_CHAT_PHOTO);
    }

}
