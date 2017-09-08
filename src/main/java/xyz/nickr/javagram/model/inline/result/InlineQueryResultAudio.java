package xyz.nickr.javagram.model.inline.result;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.inline.InlineQueryResult;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryResultAudio extends InlineQueryResult<InlineQueryResultAudio> {

    private String title;
    private String audioUrl;
    private Optional<Integer> audioDuration = Optional.empty();
    private Optional<String> caption = Optional.empty();
    private Optional<String> performer = Optional.empty();

    public InlineQueryResultAudio(TelegramBot bot) {
        super(bot, "audio");
    }

    public InlineQueryResultAudio setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultAudio setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultAudio setPerformer(String performer) {
        this.performer = Optional.ofNullable(performer);
        return this;
    }

    public InlineQueryResultAudio setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
        return this;
    }

    public InlineQueryResultAudio setAudioDuration(Integer audioDuration) {
        this.audioDuration = Optional.ofNullable(audioDuration);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        if (audioUrl == null)
            throw new InvalidModelParameterException(this, "audio_url");
        JSONObject json = super.toJSON();
        json.put("title", title);
        json.put("audio_url", audioUrl);
        caption.ifPresent(x -> json.put("caption", x));
        performer.ifPresent(x -> json.put("performer", x));
        audioDuration.ifPresent(x -> json.put("audio_duration", x));
        return json;
    }
}
