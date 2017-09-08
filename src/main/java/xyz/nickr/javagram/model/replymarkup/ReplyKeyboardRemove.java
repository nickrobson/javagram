package xyz.nickr.javagram.model.replymarkup;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReplyKeyboardRemove extends ReplyMarkup {

    private Optional<Boolean> selective = Optional.empty();

    public ReplyKeyboardRemove(TelegramBot bot) {
        super(bot);
    }

    public ReplyKeyboardRemove setSelective(Boolean selective) {
        this.selective = Optional.ofNullable(selective);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("remove_keyboard", true);
        selective.ifPresent(b -> json.put("selective", b));
        return json;
    }

}
