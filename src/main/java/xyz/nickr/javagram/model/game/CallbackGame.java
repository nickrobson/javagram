package xyz.nickr.javagram.model.game;

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
public class CallbackGame extends Model {

    public CallbackGame(TelegramBot bot) {
        super(bot);
    }

    public JSONObject toJSON() {
        return new JSONObject();
    }

}
