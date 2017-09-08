package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.FileMessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class AudioMessageContent extends FileMessageContent {

    private final int duration; // seconds
    private final Optional<String> performer;
    private final Optional<String> title;
    private final Optional<String> mimeType;
    private final Optional<Long> fileSize;

    public AudioMessageContent(TelegramBot bot, JSONObject audio) {
        super(bot, MessageContentType.AUDIO, audio);
        this.duration = audio.getInt("duration");
        this.performer = Optional.ofNullable(audio.optString("performer"));
        this.title = Optional.ofNullable(audio.optString("title"));
        this.mimeType = Optional.ofNullable(audio.optString("mime_type"));
        this.fileSize = Optional.ofNullable(audio.has("file_size") ? audio.getLong("file_size") : null);
    }

}
