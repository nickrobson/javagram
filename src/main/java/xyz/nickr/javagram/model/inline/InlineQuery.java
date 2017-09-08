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
public class InlineQuery extends Model {

    private final String id;
    private final User user;
    private final Optional<Location> location;
    private final String query;
    private final String offset;

    public InlineQuery(TelegramBot bot, JSONObject inline_query) {
        super(bot);
        this.id = inline_query.getString("id");
        this.user = User.from(bot, inline_query.getJSONObject("from"));
        this.location = Optional.ofNullable(Location.from(bot, inline_query.optJSONObject("location")));
        this.query = inline_query.getString("query");
        this.offset = inline_query.getString("offset");
    }

    public static InlineQuery from(TelegramBot bot, JSONObject inline_query) {
        return inline_query != null
                ? new InlineQuery(bot, inline_query)
                : null;
    }

}
