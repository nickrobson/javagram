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
public class InlineQueryResultLocation extends InlineQueryResult<InlineQueryResultLocation> {

    private double latitude;
    private double longitude;
    private String title;
    private Optional<String> thumbUrl = Optional.empty();
    private Optional<Integer> thumbWidth = Optional.empty();
    private Optional<Integer> thumbHeight = Optional.empty();

    public InlineQueryResultLocation(TelegramBot bot) {
        super(bot, "location");
    }

    public InlineQueryResultLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public InlineQueryResultLocation setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public InlineQueryResultLocation setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultLocation setThumbUrl(String thumbUrl) {
        this.thumbUrl = Optional.ofNullable(thumbUrl);
        return this;
    }

    public InlineQueryResultLocation setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = Optional.ofNullable(thumbWidth);
        return this;
    }

    public InlineQueryResultLocation setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = Optional.ofNullable(thumbHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        JSONObject json = super.toJSON();
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        json.put("title", title);
        thumbUrl.ifPresent(x -> json.put("thumb_url", x));
        thumbWidth.ifPresent(x -> json.put("thumb_width", x));
        thumbHeight.ifPresent(x -> json.put("thumb_height", x));
        return json;
    }
}
