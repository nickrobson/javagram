package xyz.nickr.javagram.model.payment;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class OrderInfo extends Model {

    private final Optional<String> name;
    private final Optional<String> phoneNumber;
    private final Optional<String> email;
    private final Optional<ShippingAddress> shippingAddress;

    public OrderInfo(TelegramBot bot, JSONObject order_info) {
        super(bot);
        this.name = Optional.ofNullable(order_info.optString("name"));
        this.phoneNumber = Optional.ofNullable(order_info.optString("phone_number"));
        this.email = Optional.ofNullable(order_info.optString("email"));
        this.shippingAddress = Optional.ofNullable(ShippingAddress.from(bot, order_info.optJSONObject("shipping_address")));
    }

    public static OrderInfo from(TelegramBot bot, JSONObject order_info) {
        return order_info != null
                ? new OrderInfo(bot, order_info)
                : null;
    }

}
