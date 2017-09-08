package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.PhotoSize;
import xyz.nickr.javagram.model.message.FileMessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DocumentMessageContent extends FileMessageContent {

    private final Optional<PhotoSize> thumb;
    private final Optional<String> fileName;
    private final Optional<String> mimeType;
    private final Optional<Long> fileSize;
    private final Optional<String> caption;

    public DocumentMessageContent(TelegramBot bot, JSONObject document, String caption) {
        super(bot, MessageContentType.DOCUMENT, document);
        this.thumb = Optional.ofNullable(PhotoSize.from(bot, document.optJSONObject("thumb")));
        this.fileName = Optional.ofNullable(document.optString("file_name"));
        this.mimeType = Optional.ofNullable(document.optString("mime_type"));
        this.fileSize = Optional.ofNullable(document.has("file_size") ? document.getLong("file_size") : null);
        this.caption = Optional.ofNullable(caption);
    }

}
