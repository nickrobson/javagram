package xyz.nickr.javagram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Location extends Model {

    private final double latitude;
    private final double longitude;

    public Location(TelegramBot bot, JSONObject location) {
        super(bot);
        this.latitude = location.getDouble("latitude");
        this.longitude = location.getDouble("longitude");
    }

    public static Location from(TelegramBot bot, JSONObject location) {
        return location != null
                ? new Location(bot, location)
                : null;
    }

}
