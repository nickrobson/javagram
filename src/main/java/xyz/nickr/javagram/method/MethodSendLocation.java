package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendLocation extends Method.MethodSend<MethodSendLocation> {

    private double latitude;
    private double longitude;

    public MethodSendLocation() {
        super("sendLocation");
    }

    public MethodSendLocation setLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public MethodSendLocation setLongitude(long longitude) {
        this.longitude = longitude;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        json.put("latitude", latitude);
        json.put("longitude", longitude);
        return json;
    }

}
