package xyz.nickr.javagram.model.inline;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Location;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class ChosenInlineResult extends Model {

    private final String resultId;
    private final User from;
    private final Optional<Location> location;
    private final Optional<String> inlineMessageId;
    private final String query;

    public ChosenInlineResult(TelegramBot bot, JSONObject chosen_inline_result) {
        super(bot);
        this.resultId = chosen_inline_result.getString("result_id");
        this.from = User.from(bot, chosen_inline_result.getJSONObject("from"));
        this.location = Optional.ofNullable(Location.from(bot, chosen_inline_result.optJSONObject("location")));
        this.inlineMessageId = Optional.ofNullable(chosen_inline_result.optString("inline_message_id"));
        this.query = chosen_inline_result.getString("query");
    }

    public static ChosenInlineResult from(TelegramBot bot, JSONObject chosen_inline_result) {
        return chosen_inline_result != null
                ? new ChosenInlineResult(bot, chosen_inline_result)
                : null;
    }

}
