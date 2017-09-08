package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodAnswerCallbackQuery extends Method<Boolean> {

    private String callbackQueryId;
    private Optional<String> text = Optional.empty();
    private Optional<Boolean> showAlert = Optional.empty();
    private Optional<String> url = Optional.empty();
    private Optional<Integer> cacheTime = Optional.empty();

    public MethodAnswerCallbackQuery() {
        super("answerCallbackQuery");
    }

    public MethodAnswerCallbackQuery setCallbackQueryId(String callbackQueryId) {
        this.callbackQueryId = callbackQueryId;
        return this;
    }

    public MethodAnswerCallbackQuery setText(String text) {
        this.text = Optional.ofNullable(text);
        return this;
    }

    public MethodAnswerCallbackQuery setShowAlert(Boolean showAlert) {
        this.showAlert = Optional.ofNullable(showAlert);
        return this;
    }

    public MethodAnswerCallbackQuery setUrl(String url) {
        this.url = Optional.ofNullable(url);
        return this;
    }

    public MethodAnswerCallbackQuery setCacheTime(Integer cacheTime) {
        this.cacheTime = Optional.ofNullable(cacheTime);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (callbackQueryId == null)
            throw new InvalidMethodParameterException(this, "callback_query_id");
        json.put("callback_query_id", callbackQueryId);
        text.ifPresent(s -> json.put("text", s));
        showAlert.ifPresent(b -> json.put("show_alert", b));
        url.ifPresent(s -> json.put("url", s));
        cacheTime.ifPresent(i -> json.put("cache_time", i));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
