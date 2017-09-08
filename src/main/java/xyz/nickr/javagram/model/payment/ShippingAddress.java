package xyz.nickr.javagram.model.payment;

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
public class ShippingAddress extends Model {

    private final String countryCode;
    private final String state;
    private final String city;
    private final String streetLine1;
    private final String streetLine2;
    private final String postCode;

    public ShippingAddress(TelegramBot bot, JSONObject shipping_address) {
        super(bot);
        this.countryCode = shipping_address.getString("country_code");
        this.state = shipping_address.getString("state");
        this.city = shipping_address.getString("city");
        this.streetLine1 = shipping_address.getString("street_line1");
        this.streetLine2 = shipping_address.getString("street_line2");
        this.postCode = shipping_address.getString("post_code");
    }

    public static ShippingAddress from(TelegramBot bot, JSONObject shipping_address) {
        return shipping_address != null
                ? new ShippingAddress(bot, shipping_address)
                : null;
    }

}
