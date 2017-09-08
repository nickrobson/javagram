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
public class VoiceMessageContent extends FileMessageContent {

    private final int duration; // seconds
    private final Optional<String> mimeType;
    private final Optional<Long> fileSize;

    public VoiceMessageContent(TelegramBot bot, JSONObject voice) {
        super(bot, MessageContentType.VOICE, voice);
        this.duration = voice.getInt("duration");
        this.mimeType = Optional.ofNullable(voice.optString("mime_type"));
        this.fileSize = Optional.ofNullable(voice.has("file_size") ? voice.getLong("file_size") : null);
    }

}
