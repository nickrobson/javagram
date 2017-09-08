package xyz.nickr.javagram.model.inline.cached;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryResultCachedAudio extends InlineQueryResultCached<InlineQueryResultCachedAudio> {

    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedAudio(TelegramBot bot) {
        super(bot, "audio", "audio_file_id");
    }

    public InlineQueryResultCachedAudio setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

}
