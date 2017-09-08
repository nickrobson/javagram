package xyz.nickr.javagram.model.message.service;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContentType;
import xyz.nickr.javagram.model.message.ServiceMessageContent;
import xyz.nickr.javagram.model.PhotoSize;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NewChatPhotoContent extends ServiceMessageContent {

    private PhotoSize[] photoSizes;

    public NewChatPhotoContent(TelegramBot bot, JSONArray photos) {
        super(bot, MessageContentType.NEW_CHAT_PHOTO);
        this.photoSizes = new PhotoSize[photos.length()];
        for (int i = 0; i < photos.length(); i++) {
            this.photoSizes[i] = new PhotoSize(bot, photos.getJSONObject(i));
        }
    }

}
