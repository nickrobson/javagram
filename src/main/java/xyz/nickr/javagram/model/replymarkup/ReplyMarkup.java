package xyz.nickr.javagram.model.replymarkup;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public class ReplyMarkup extends Model {

    public ReplyMarkup(TelegramBot bot) {
        super(bot);
    }

    public JSONObject toJSON() {
        return new JSONObject();
    }

}
