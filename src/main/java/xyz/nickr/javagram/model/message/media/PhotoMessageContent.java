package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.PhotoSize;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PhotoMessageContent extends MessageContent {

    private PhotoSize[] photoSizes;
    private Optional<String> caption;

    public PhotoMessageContent(TelegramBot bot, JSONArray photos, String caption) {
        super(bot, MessageContentType.PHOTO);
        this.photoSizes = new PhotoSize[photos.length()];
        for (int i = 0; i < photos.length(); i++) {
            this.photoSizes[i] = new PhotoSize(bot, photos.getJSONObject(i));
        }
        this.caption = Optional.ofNullable(caption);
    }

}
