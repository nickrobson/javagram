package xyz.nickr.javagram.model.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingQuery extends Model {

    private final String id;
    private final User user;
    private final String invoicePayload;
    private final ShippingAddress shippingAddress;

    public ShippingQuery(TelegramBot bot, JSONObject shipping_query) {
        super(bot);
        this.id = shipping_query.getString("id");
        this.user = User.from(bot, shipping_query.getJSONObject("from"));
        this.invoicePayload = shipping_query.getString("invoice_payload");
        this.shippingAddress = ShippingAddress.from(bot, shipping_query.getJSONObject("shipping_address"));
    }

    public static ShippingQuery from(TelegramBot bot, JSONObject shipping_query) {
        return shipping_query != null
                ? new ShippingQuery(bot, shipping_query)
                : null;
    }

}
