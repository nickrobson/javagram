package xyz.nickr.javagram.model.message.media;

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
public class LocationMessageContent extends MessageContent {

    private final double latitude;
    private final double longitude;

    public LocationMessageContent(TelegramBot bot, JSONObject location) {
        super(bot, MessageContentType.LOCATION);
        this.latitude = location.getDouble("latitude");
        this.longitude = location.getDouble("longitude");
    }

}
