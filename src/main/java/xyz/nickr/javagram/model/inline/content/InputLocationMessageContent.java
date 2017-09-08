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
public class InputLocationMessageContent extends InputMessageContent {

    private Optional<Double> latitude = Optional.empty();
    private Optional<Double> longitude = Optional.empty();

    public InputLocationMessageContent(TelegramBot bot) {
        super(bot);
    }

    public InputLocationMessageContent setLatitude(double latitude) {
        this.latitude = Optional.ofNullable(latitude);
        return this;
    }

    public InputLocationMessageContent setLongitude(double longitude) {
        this.longitude = Optional.ofNullable(longitude);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (!latitude.isPresent())
            throw new InvalidModelParameterException(this, "latitude");
        if (!longitude.isPresent())
            throw new InvalidModelParameterException(this, "longitude");
        JSONObject json = new JSONObject();
        json.put("latitude", latitude.get());
        json.put("longitude", longitude.get());
        return json;
    }

}
