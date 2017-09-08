package xyz.nickr.javagram.method;

import java.util.Optional;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;

/**
 * @author Nick Robson
 */
public class MethodAnswerPreCheckoutQuery extends Method<Boolean> {

    private String preCheckoutQueryId;
    private Boolean ok;
    private Optional<String> errorMessage = Optional.empty();

    public MethodAnswerPreCheckoutQuery() {
        super("answerPreCheckoutQuery");
    }

    public MethodAnswerPreCheckoutQuery setPreCheckoutQueryId(String preCheckoutQueryId) {
        this.preCheckoutQueryId = preCheckoutQueryId;
        return this;
    }

    public MethodAnswerPreCheckoutQuery setOkay() {
        this.ok = true;
        return this;
    }

    public MethodAnswerPreCheckoutQuery setNotOkay(String errorMessage) {
        this.ok = false;
        this.errorMessage = Optional.ofNullable(errorMessage);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        if (preCheckoutQueryId == null)
            throw new InvalidMethodParameterException(this, "pre_checkout_query_id");
        if (ok == null)
            throw new InvalidMethodParameterException(this, "ok");
        JSONObject json = new JSONObject();
        json.put("pre_checkout_query_id", preCheckoutQueryId);
        json.put("ok", ok);
        if (!ok)
            json.put("error_message", errorMessage);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
