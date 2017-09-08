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
public class InlineQueryResultCachedMpeg4Gif extends InlineQueryResultCached<InlineQueryResultCachedMpeg4Gif> {

    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedMpeg4Gif(TelegramBot bot) {
        super(bot, "mpeg4_gif", "mpeg4_file_id");
    }

    public InlineQueryResultCachedMpeg4Gif setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultCachedMpeg4Gif setCaption(String caption) {
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
