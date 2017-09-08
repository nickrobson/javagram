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
public class VideoNoteMessageContent extends FileMessageContent {

    private final int length;
    private final int duration; // seconds
    private final Optional<PhotoSize> thumb;
    private final Optional<Long> fileSize;

    public VideoNoteMessageContent(TelegramBot bot, JSONObject video_note) {
        super(bot, MessageContentType.VIDEO_NOTE, video_note);
        this.length = video_note.getInt("length");
        this.duration = video_note.getInt("duration");
        this.thumb = Optional.ofNullable(PhotoSize.from(bot, video_note.optJSONObject("thumb")));
        this.fileSize = Optional.ofNullable(video_note.has("file_size") ? video_note.getLong("file_size") : null);
    }

}
