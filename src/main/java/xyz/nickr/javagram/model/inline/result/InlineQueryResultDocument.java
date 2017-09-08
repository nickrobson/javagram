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
public class InlineQueryResultDocument extends InlineQueryResult<InlineQueryResultDocument> {

    private String title;
    private Optional<String> caption = Optional.empty();
    private String documentUrl;
    private String mimeType;
    private Optional<String> description = Optional.empty();
    private Optional<String> thumbUrl = Optional.empty();
    private Optional<Integer> thumbWidth = Optional.empty();
    private Optional<Integer> thumbHeight = Optional.empty();

    public InlineQueryResultDocument(TelegramBot bot) {
        super(bot, "document");
    }

    public InlineQueryResultDocument setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultDocument setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultDocument setDocumentUrl(String documentUrl) {
        this.documentUrl = documentUrl;
        return this;
    }

    public InlineQueryResultDocument setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public InlineQueryResultDocument setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public InlineQueryResultDocument setThumbUrl(String thumbUrl) {
        this.thumbUrl = Optional.ofNullable(thumbUrl);
        return this;
    }

    public InlineQueryResultDocument setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = Optional.ofNullable(thumbWidth);
        return this;
    }

    public InlineQueryResultDocument setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = Optional.ofNullable(thumbHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        if (documentUrl == null)
            throw new InvalidModelParameterException(this, "document_url");
        if (mimeType == null)
            throw new InvalidModelParameterException(this, "mime_type");
        JSONObject json = super.toJSON();
        json.put("title", title);
        caption.ifPresent(x -> json.put("caption", x));
        json.put("document_url", documentUrl);
        json.put("mime_type", mimeType);
        description.ifPresent(x -> json.put("description", x));
        thumbUrl.ifPresent(x -> json.put("thumb_url", x));
        thumbWidth.ifPresent(x -> json.put("thumb_width", x));
        thumbHeight.ifPresent(x -> json.put("thumb_height", x));
        return json;
    }
}
