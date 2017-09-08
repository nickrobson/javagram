package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class VenueMessageContent extends MessageContent {

    private final double latitude;
    private final double longitude;
    private final String title;
    private final String address;
    private final Optional<String> foursquareId;

    public VenueMessageContent(TelegramBot bot, JSONObject venue) {
        super(bot, MessageContentType.VENUE);
        JSONObject location = venue.getJSONObject("location");
        this.latitude = location.getDouble("latitude");
        this.longitude = location.getDouble("longitude");
        this.title = venue.getString("title");
        this.address = venue.getString("address");
        this.foursquareId = Optional.ofNullable(venue.optString("foursquare_id"));
    }

}
