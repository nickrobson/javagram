package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.inline.InlineQuery;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryUpdate extends Update {

    private final InlineQuery query;

    public InlineQueryUpdate(TelegramBot bot, long updateId, JSONObject inline_query) {
        super(bot, UpdateType.INLINE_QUERY, updateId);
        this.query = InlineQuery.from(bot, inline_query);
    }

}
