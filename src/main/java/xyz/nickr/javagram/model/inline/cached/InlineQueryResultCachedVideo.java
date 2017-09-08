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
public class InlineQueryResultCachedVideo extends InlineQueryResultCached<InlineQueryResultCachedVideo> {

    private Optional<String> title = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedVideo(TelegramBot bot) {
        super(bot, "video", "video_file_id");
    }

    public InlineQueryResultCachedVideo setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultCachedVideo setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public InlineQueryResultCachedVideo setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        title.ifPresent(s -> json.put("title", s));
        description.ifPresent(s -> json.put("description", s));
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

}
