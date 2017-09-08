package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendVenue extends Method.MethodSend<MethodSendVenue> {

    private double latitude;
    private double longitude;
    private String title;
    private String address;
    private Optional<String> foursquareId = Optional.empty();

    public MethodSendVenue() {
        super("sendVenue");
    }

    public MethodSendVenue setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MethodSendVenue setLongitude(long longitude) {
        this.longitude = longitude;
        return this;
    }

    public MethodSendVenue setTitle(String title) {
        this.title = title;
        return this;
    }

    public MethodSendVenue setAddress(String address) {
        this.address = address;
        return this;
    }

    public MethodSendVenue setFoursquareId(String foursquareId) {
        this.foursquareId = Optional.ofNullable(foursquareId);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (title == null)
            throw new InvalidMethodParameterException(this, "title");
        if (address == null)
            throw new InvalidMethodParameterException(this, "address");
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        json.put("title", title);
        json.put("address", address);
        foursquareId.ifPresent(s -> json.put("foursquare_id", s));
        return json;
    }

}
