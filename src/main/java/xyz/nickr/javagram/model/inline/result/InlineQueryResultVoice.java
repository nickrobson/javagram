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
public class InlineQueryResultVoice extends InlineQueryResult<InlineQueryResultVoice> {

    private String title;
    private String voiceUrl;
    private Optional<Integer> voiceDuration = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultVoice(TelegramBot bot) {
        super(bot, "voice");
    }

    public InlineQueryResultVoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultVoice setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultVoice setVoiceUrl(String voiceUrl) {
        this.voiceUrl = voiceUrl;
        return this;
    }

    public InlineQueryResultVoice setVoiceDuration(Integer voiceDuration) {
        this.voiceDuration = Optional.ofNullable(voiceDuration);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        if (voiceUrl == null)
            throw new InvalidModelParameterException(this, "voice_url");
        JSONObject json = super.toJSON();
        json.put("title", title);
        json.put("voice_url", voiceUrl);
        caption.ifPresent(x -> json.put("caption", x));
        voiceDuration.ifPresent(x -> json.put("voice_duration", x));
        return json;
    }
}
