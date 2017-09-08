package xyz.nickr.javagram.model.inline.content;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InputVenueMessageContent extends InputMessageContent {

    private Optional<Double> latitude = Optional.empty();
    private Optional<Double> longitude = Optional.empty();
    private String title;
    private String address;
    private Optional<String> foursquareId = Optional.empty();

    public InputVenueMessageContent(TelegramBot bot) {
        super(bot);
    }

    public InputVenueMessageContent setLatitude(double latitude) {
        this.latitude = Optional.ofNullable(latitude);
        return this;
    }

    public InputVenueMessageContent setLongitude(double longitude) {
        this.longitude = Optional.ofNullable(longitude);
        return this;
    }

    public InputVenueMessageContent setTitle(String title) {
        this.title = title;
        return this;
    }

    public InputVenueMessageContent setAddress(String address) {
        this.address = address;
        return this;
    }

    public InputVenueMessageContent setFoursquareId(String foursquareId) {
        this.foursquareId = Optional.ofNullable(foursquareId);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (!latitude.isPresent())
            throw new InvalidModelParameterException(this, "latitude");
        if (!longitude.isPresent())
            throw new InvalidModelParameterException(this, "longitude");
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        if (address == null)
            throw new InvalidModelParameterException(this, "address");
        JSONObject json = new JSONObject();
        json.put("latitude", latitude.get());
        json.put("longitude", longitude.get());
        json.put("title", title);
        json.put("address", address);
        foursquareId.ifPresent(s -> json.put("foursquare_id", s));
        return json;
    }

}
