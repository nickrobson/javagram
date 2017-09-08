package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.CallbackQuery;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CallbackQueryUpdate extends Update {

    private final CallbackQuery query;

    public CallbackQueryUpdate(TelegramBot bot, long updateId, JSONObject callback_query) {
        super(bot, UpdateType.CALLBACK_QUERY, updateId);
        this.query = CallbackQuery.from(bot, callback_query);
    }

}
