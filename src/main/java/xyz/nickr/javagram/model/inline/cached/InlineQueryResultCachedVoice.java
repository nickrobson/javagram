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
public class InlineQueryResultCachedVoice extends InlineQueryResultCached<InlineQueryResultCachedVoice> {

    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedVoice(TelegramBot bot) {
        super(bot, "voice", "voice_file_id");
    }

    public InlineQueryResultCachedVoice setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultCachedVoice setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        title.ifPresent(s -> json.put("title", s));
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

}
