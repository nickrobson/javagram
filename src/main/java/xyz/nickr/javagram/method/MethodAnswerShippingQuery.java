package xyz.nickr.javagram.method;

import java.util.Optional;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.payment.ShippingOption;

/**
 * @author Nick Robson
 */
public class MethodAnswerShippingQuery extends Method<Boolean> {

    private String shippingQueryId;
    private Boolean ok;
    private ShippingOption[] shippingOptions;
    private Optional<String> errorMessage = Optional.empty();

    public MethodAnswerShippingQuery() {
        super("answerShippingQuery");
    }

    public MethodAnswerShippingQuery setShippingQueryId(String shippingQueryId) {
        this.shippingQueryId = shippingQueryId;
        return this;
    }

    public MethodAnswerShippingQuery setOkay(ShippingOption[] shippingOptions) {
        this.ok = true;
        this.shippingOptions = shippingOptions;
        return this;
    }

    public MethodAnswerShippingQuery setNotOkay(String errorMessage) {
        this.ok = false;
        this.errorMessage = Optional.ofNullable(errorMessage);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        if (shippingQueryId == null)
            throw new InvalidMethodParameterException(this, "shipping_query_id");
        if (ok == null)
            throw new InvalidMethodParameterException(this, "ok");
        JSONObject json = new JSONObject();
        json.put("shipping_query_id", shippingQueryId);
        json.put("ok", ok);
        if (ok) {
            JSONArray arr = new JSONArray();
            for (ShippingOption option : shippingOptions)
                arr.put(option.toJSON());
            json.put("shipping_options", arr);
        } else
            json.put("error_message", errorMessage);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
