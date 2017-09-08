package xyz.nickr.javagram.model.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ShippingOption extends Model {

    private final String id;
    private final String title;
    private final LabeledPrice[] prices;

    public ShippingOption(TelegramBot bot, JSONObject shipping_option) {
        super(bot);
        this.id = shipping_option.getString("id");
        this.title = shipping_option.getString("title");
        JSONArray arr = shipping_option.getJSONArray("prices");
        this.prices = new LabeledPrice[arr.length()];
        for (int i = 0; i < this.prices.length; i++) {
            this.prices[i] = new LabeledPrice(bot, arr.getJSONObject(i));
        }
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("title", title);
        JSONArray arr = new JSONArray();
        for (LabeledPrice price : prices)
            arr.put(price.toJSON());
        json.put("prices", arr);
        return json;
    }

}
