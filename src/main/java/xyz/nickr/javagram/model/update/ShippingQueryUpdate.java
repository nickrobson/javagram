package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.payment.ShippingQuery;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ShippingQueryUpdate extends Update {

    private final ShippingQuery query;

    public ShippingQueryUpdate(TelegramBot bot, long updateId, JSONObject shipping_query) {
        super(bot, UpdateType.SHIPPING, updateId);
        this.query = ShippingQuery.from(bot, shipping_query);
    }

}
