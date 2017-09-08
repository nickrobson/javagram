package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.payment.PreCheckoutQuery;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PreCheckoutQueryUpdate extends Update {

    private final PreCheckoutQuery query;

    public PreCheckoutQueryUpdate(TelegramBot bot, long updateId, JSONObject pre_checkout_query) {
        super(bot, UpdateType.PRE_CHECKOUT, updateId);
        this.query = PreCheckoutQuery.from(bot, pre_checkout_query);
    }

}
