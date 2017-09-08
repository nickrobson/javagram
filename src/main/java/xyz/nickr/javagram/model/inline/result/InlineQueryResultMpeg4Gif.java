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
public class InlineQueryResultMpeg4Gif extends InlineQueryResult<InlineQueryResultMpeg4Gif> {

    private String mpeg4Url;
    private String thumbUrl;
    private Optional<Integer> mpeg4Width = Optional.empty();
    private Optional<Integer> mpeg4Height = Optional.empty();
    private Optional<Integer> mpeg4Duration = Optional.empty();
    private Optional<String> title = Optional.empty();
    private Optional<String> caption = Optional.empty();

    public InlineQueryResultMpeg4Gif(TelegramBot bot) {
        super(bot, "mpeg4_gif");
    }

    public InlineQueryResultMpeg4Gif setMpeg4Url(String mpeg4Url) {
        this.mpeg4Url = mpeg4Url;
        return this;
    }

    public InlineQueryResultMpeg4Gif setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public InlineQueryResultMpeg4Gif setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    public InlineQueryResultMpeg4Gif setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultMpeg4Gif setMpeg4Width(Integer mpeg4Width) {
        this.mpeg4Width = Optional.ofNullable(mpeg4Width);
        return this;
    }

    public InlineQueryResultMpeg4Gif setMpeg4Height(Integer mpeg4Height) {
        this.mpeg4Height = Optional.ofNullable(mpeg4Height);
        return this;
    }

    public InlineQueryResultMpeg4Gif setMpeg4Duration(Integer mpeg4Duration) {
        this.mpeg4Duration = Optional.ofNullable(mpeg4Duration);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (mpeg4Url == null)
            throw new InvalidModelParameterException(this, "mpeg4_url");
        if (thumbUrl == null)
            throw new InvalidModelParameterException(this, "thumb_url");
        JSONObject json = super.toJSON();
        json.put("mpeg4_url", mpeg4Url);
        json.put("thumb_url", thumbUrl);
        title.ifPresent(x -> json.put("title", x));
        caption.ifPresent(x -> json.put("caption", x));
        mpeg4Width.ifPresent(x -> json.put("mpeg4_width", x));
        mpeg4Height.ifPresent(x -> json.put("mpeg4_height", x));
        mpeg4Duration.ifPresent(x -> json.put("mpeg4_duration", x));
        return json;
    }
}
