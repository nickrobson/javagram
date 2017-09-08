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
public class InlineQueryResultCachedDocument extends InlineQueryResultCached<InlineQueryResultCachedDocument> {

    private Optional<String> description = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultCachedDocument(TelegramBot bot) {
        super(bot, "document", "document_file_id");
    }

    public InlineQueryResultCachedDocument setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public InlineQueryResultCachedDocument setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        description.ifPresent(s -> json.put("description", s));
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

}
