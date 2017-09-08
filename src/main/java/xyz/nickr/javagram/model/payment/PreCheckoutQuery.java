package xyz.nickr.javagram.model.payment;

import java.util.Optional;
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
public class PreCheckoutQuery extends Model {

    private final String id;
    private final User user;
    private final String currency;
    private final int totalAmount;
    private final String invoicePayload;
    private final Optional<String> shippingOptionId;
    private final Optional<OrderInfo> orderInfo;

    public PreCheckoutQuery(TelegramBot bot, JSONObject pre_checkout_query) {
        super(bot);
        this.id = pre_checkout_query.getString("id");
        this.user = User.from(bot, pre_checkout_query.getJSONObject("from"));
        this.currency = pre_checkout_query.getString("currency");
        this.totalAmount = pre_checkout_query.getInt("totalAmount");
        this.invoicePayload = pre_checkout_query.getString("invoice_payload");
        this.shippingOptionId = Optional.ofNullable(pre_checkout_query.optString("shipping_option_id"));
        this.orderInfo = Optional.ofNullable(OrderInfo.from(bot, pre_checkout_query.optJSONObject("order_info")));
    }

    public static PreCheckoutQuery from(TelegramBot bot, JSONObject pre_checkout_query) {
        return pre_checkout_query != null
                ? new PreCheckoutQuery(bot, pre_checkout_query)
                : null;
    }

}
