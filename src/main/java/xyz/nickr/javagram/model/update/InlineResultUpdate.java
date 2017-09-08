package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.inline.ChosenInlineResult;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineResultUpdate extends Update {

    private final ChosenInlineResult chosenResult;

    public InlineResultUpdate(TelegramBot bot, long updateId, JSONObject chosen_inline_result) {
        super(bot, UpdateType.INLINE_RESULT, updateId);
        this.chosenResult = ChosenInlineResult.from(bot, chosen_inline_result);
    }

}
