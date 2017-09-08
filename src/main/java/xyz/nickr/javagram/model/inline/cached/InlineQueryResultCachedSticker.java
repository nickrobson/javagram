package xyz.nickr.javagram.model.inline.cached;

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
public class InlineQueryResultCachedSticker extends InlineQueryResultCached<InlineQueryResultCachedSticker> {

    public InlineQueryResultCachedSticker(TelegramBot bot) {
        super(bot, "sticker", "sticker_file_id");
    }

    @Override
    public JSONObject toJSON() {
        return super.toJSON();
    }

}
