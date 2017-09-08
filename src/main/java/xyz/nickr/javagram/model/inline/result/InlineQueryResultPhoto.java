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
public class InlineQueryResultPhoto extends InlineQueryResult<InlineQueryResultPhoto> {

    private String photoUrl;
    private String thumbUrl;
    private Optional<Integer> photoWidth = Optional.empty();
    private Optional<Integer> photoHeight = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();
    private Optional<String> description = Optional.empty();

    public InlineQueryResultPhoto(TelegramBot bot) {
        super(bot, "photo");
    }

    public InlineQueryResultPhoto setGifUrl(String photoUrl) {
        this.photoUrl = photoUrl;
        return this;
    }

    public InlineQueryResultPhoto setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public InlineQueryResultPhoto setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultPhoto setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultPhoto setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public InlineQueryResultPhoto setGifWidth(Integer photoWidth) {
        this.photoWidth = Optional.ofNullable(photoWidth);
        return this;
    }

    public InlineQueryResultPhoto setGifHeight(Integer photoHeight) {
        this.photoHeight = Optional.ofNullable(photoHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (photoUrl == null)
            throw new InvalidModelParameterException(this, "photo_url");
        if (thumbUrl == null)
            throw new InvalidModelParameterException(this, "thumb_url");
        JSONObject json = super.toJSON();
        json.put("photo_url", photoUrl);
        json.put("thumb_url", thumbUrl);
        title.ifPresent(x -> json.put("title", x));
        caption.ifPresent(x -> json.put("caption", x));
        description.ifPresent(x -> json.put("description", x));
        photoWidth.ifPresent(x -> json.put("photo_width", x));
        photoHeight.ifPresent(x -> json.put("photo_height", x));
        return json;
    }
}
