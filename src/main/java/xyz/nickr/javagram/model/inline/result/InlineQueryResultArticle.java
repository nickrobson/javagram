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
public class InlineQueryResultArticle extends InlineQueryResult<InlineQueryResultArticle> {

    private String title;
    private Optional<String> url = Optional.empty();
    private Optional<Boolean> hideUrl = Optional.empty();
    private Optional<String> description = Optional.empty();
    private Optional<String> thumbUrl = Optional.empty();
    private Optional<Integer> thumbWidth = Optional.empty();
    private Optional<Integer> thumbHeight = Optional.empty();

    public InlineQueryResultArticle(TelegramBot bot) {
        super(bot, "article");
    }

    public InlineQueryResultArticle setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultArticle setUrl(String url) {
        this.url = Optional.ofNullable(url);
        return this;
    }

    public InlineQueryResultArticle setHideUrl(boolean hideUrl) {
        this.hideUrl = Optional.ofNullable(hideUrl);
        return this;
    }

    public InlineQueryResultArticle setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    public InlineQueryResultArticle setThumbUrl(String thumbUrl) {
        this.thumbUrl = Optional.ofNullable(thumbUrl);
        return this;
    }

    public InlineQueryResultArticle setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = Optional.ofNullable(thumbWidth);
        return this;
    }

    public InlineQueryResultArticle setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = Optional.ofNullable(thumbHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        JSONObject json = super.toJSON();
        json.put("title", title);
        url.ifPresent(x -> json.put("url", x));
        hideUrl.ifPresent(x -> json.put("hide_url", x));
        description.ifPresent(x -> json.put("description", x));
        thumbUrl.ifPresent(x -> json.put("thumb_url", x));
        thumbWidth.ifPresent(x -> json.put("thumb_width", x));
        thumbHeight.ifPresent(x -> json.put("thumb_height", x));
        return json;
    }
}
