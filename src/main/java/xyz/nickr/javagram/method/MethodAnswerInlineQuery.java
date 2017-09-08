package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.inline.InlineQueryResult;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodAnswerInlineQuery extends Method<Boolean> {

    private String inlineQueryId;
    private InlineQueryResult[] results;
    private Optional<Integer> cacheTime = Optional.empty();
    private Optional<Boolean> isPersonal = Optional.empty();
    private Optional<String> nextOffset = Optional.empty();
    private Optional<String> switchPmText = Optional.empty();
    private Optional<String> switchPmParameter = Optional.empty();

    public MethodAnswerInlineQuery() {
        super("answerInlineQuery");
    }

    public MethodAnswerInlineQuery setInlineQueryId(String inlineQueryId) {
        this.inlineQueryId = inlineQueryId;
        return this;
    }

    public MethodAnswerInlineQuery setResults(InlineQueryResult[] results) {
        this.results = results;
        return this;
    }

    public MethodAnswerInlineQuery setCacheTime(Integer cacheTime) {
        this.cacheTime = Optional.ofNullable(cacheTime);
        return this;
    }

    public MethodAnswerInlineQuery setIsPersonal(Boolean isPersonal) {
        this.isPersonal = Optional.ofNullable(isPersonal);
        return this;
    }

    public MethodAnswerInlineQuery setNextOffset(String nextOffset) {
        this.nextOffset = Optional.ofNullable(nextOffset);
        return this;
    }

    public MethodAnswerInlineQuery setSwitchPmText(String switchPmText) {
        this.switchPmText = Optional.ofNullable(switchPmText);
        return this;
    }

    public MethodAnswerInlineQuery setSwitchPmParameter(String switchPmParameter) {
        this.switchPmParameter = Optional.ofNullable(switchPmParameter);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        if (inlineQueryId == null)
            throw new InvalidMethodParameterException(this, "inline_query_id");
        if (results == null)
            throw new InvalidMethodParameterException(this, "results");
        JSONObject json = new JSONObject();
        json.put("inline_query_id", inlineQueryId);
        JSONArray arr = new JSONArray();
        for (InlineQueryResult result : results)
            arr.put(result.toJSON());
        json.put("results", arr);
        cacheTime.ifPresent(i -> json.put("cache_time", i));
        isPersonal.ifPresent(b -> json.put("is_personal", b));
        nextOffset.ifPresent(s -> json.put("next_offset", s));
        switchPmText.ifPresent(s -> json.put("switch_pm_text", s));
        switchPmParameter.ifPresent(s -> json.put("switch_pm_parameter", s));
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
