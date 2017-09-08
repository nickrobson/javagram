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
public class VideoMessageContent extends FileMessageContent {

    private final int width;
    private final int height;
    private final int duration; // seconds
    private final Optional<PhotoSize> thumb;
    private final Optional<String> mimeType;
    private final Optional<Long> fileSize;
    private final Optional<String> caption;

    public VideoMessageContent(TelegramBot bot, JSONObject video, String caption) {
        super(bot, MessageContentType.VIDEO, video);
        this.width = video.getInt("width");
        this.height = video.getInt("height");
        this.duration = video.getInt("duration");
        this.thumb = Optional.ofNullable(PhotoSize.from(bot, video.optJSONObject("thumb")));
        this.mimeType = Optional.ofNullable(video.optString("mime_type"));
        this.fileSize = Optional.ofNullable(video.has("file_size") ? video.getLong("file_size") : null);
        this.caption = Optional.ofNullable(caption);
    }

}
