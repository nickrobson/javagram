package xyz.nickr.javagram.model.message.media;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class MaskPosition extends Model {

    private final String point;
    private final double xShift;
    private final double yShift;
    private final double scale;

    public MaskPosition(TelegramBot bot, JSONObject mask_position) {
        super(bot);
        this.point = mask_position.getString("point");
        this.xShift = mask_position.getDouble("x_shift");
        this.yShift = mask_position.getDouble("y_shift");
        this.scale = mask_position.getDouble("scale");
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("point", point);
        json.put("x_shift", xShift);
        json.put("y_shift", yShift);
        json.put("scale", scale);
        return json;
    }

    public static MaskPosition from(TelegramBot bot, JSONObject mask_position) {
        return mask_position != null
                ? new MaskPosition(bot, mask_position)
                : null;
    }

}
