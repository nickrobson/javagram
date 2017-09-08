package xyz.nickr.javagram.model.inline.content;

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
public abstract class InputMessageContent extends Model {

    public InputMessageContent(TelegramBot bot) {
        super(bot);
    }

    public abstract JSONObject toJSON();

}
