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
public class InlineQueryResultVenue extends InlineQueryResult<InlineQueryResultVenue> {

    private double latitude;
    private double longitude;
    private String title;
    private String address;
    private Optional<String> foursquareId = Optional.empty();
    private Optional<String> thumbUrl = Optional.empty();
    private Optional<Integer> thumbWidth = Optional.empty();
    private Optional<Integer> thumbHeight = Optional.empty();

    public InlineQueryResultVenue(TelegramBot bot) {
        super(bot, "venue");
    }

    public InlineQueryResultVenue setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public InlineQueryResultVenue setLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public InlineQueryResultVenue setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultVenue setAddress(String address) {
        this.address = address;
        return this;
    }

    public InlineQueryResultVenue setFoursquareId(String foursquareId) {
        this.foursquareId = Optional.ofNullable(foursquareId);
        return this;
    }

    public InlineQueryResultVenue setThumbUrl(String thumbUrl) {
        this.thumbUrl = Optional.ofNullable(thumbUrl);
        return this;
    }

    public InlineQueryResultVenue setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = Optional.ofNullable(thumbWidth);
        return this;
    }

    public InlineQueryResultVenue setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = Optional.ofNullable(thumbHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        if (address == null)
            throw new InvalidModelParameterException(this, "address");
        JSONObject json = super.toJSON();
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        json.put("title", title);
        json.put("address", address);
        foursquareId.ifPresent(x -> json.put("foursquare_id", x));
        thumbUrl.ifPresent(x -> json.put("thumb_url", x));
        thumbWidth.ifPresent(x -> json.put("thumb_width", x));
        thumbHeight.ifPresent(x -> json.put("thumb_height", x));
        return json;
    }
}
