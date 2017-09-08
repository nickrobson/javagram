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
public class InlineQueryResultCachedGif extends InlineQueryResultCached<InlineQueryResultCachedGif> {

    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedGif(TelegramBot bot) {
        super(bot, "gif", "gif_file_id");
    }

    public InlineQueryResultCachedGif setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultCachedGif setCaption(String caption) {
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
