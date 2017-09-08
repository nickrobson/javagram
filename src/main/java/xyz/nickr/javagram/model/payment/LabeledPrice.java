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
public class LabeledPrice extends Model {

    private final String label;
    private final int amount;

    public LabeledPrice(TelegramBot bot, JSONObject labeled_price) {
        super(bot);
        this.label = labeled_price.getString("label");
        this.amount = labeled_price.getInt("amount");
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("label", label);
        json.put("amount", amount);
        return json;
    }

}
