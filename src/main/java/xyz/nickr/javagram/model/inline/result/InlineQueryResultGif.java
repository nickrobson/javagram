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
public class InlineQueryResultGif extends InlineQueryResult<InlineQueryResultGif> {

    private String gifUrl;
    private String thumbUrl;
    private Optional<Integer> gifWidth = Optional.empty();
    private Optional<Integer> gifHeight = Optional.empty();
    private Optional<Integer> gifDuration = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultGif(TelegramBot bot) {
        super(bot, "gif");
    }

    public InlineQueryResultGif setGifUrl(String gifUrl) {
        this.gifUrl = gifUrl;
        return this;
    }

    public InlineQueryResultGif setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public InlineQueryResultGif setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultGif setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultGif setGifWidth(Integer gifWidth) {
        this.gifWidth = Optional.ofNullable(gifWidth);
        return this;
    }

    public InlineQueryResultGif setGifHeight(Integer gifHeight) {
        this.gifHeight = Optional.ofNullable(gifHeight);
        return this;
    }

    public InlineQueryResultGif setGifDuration(Integer gifDuration) {
        this.gifDuration = Optional.ofNullable(gifDuration);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (gifUrl == null)
            throw new InvalidModelParameterException(this, "gif_url");
        if (thumbUrl == null)
            throw new InvalidModelParameterException(this, "thumb_url");
        JSONObject json = super.toJSON();
        json.put("gif_url", gifUrl);
        json.put("thumb_url", thumbUrl);
        title.ifPresent(x -> json.put("title", x));
        caption.ifPresent(x -> json.put("caption", x));
        gifWidth.ifPresent(x -> json.put("gif_width", x));
        gifHeight.ifPresent(x -> json.put("gif_height", x));
        gifDuration.ifPresent(x -> json.put("gif_duration", x));
        return json;
    }
}
