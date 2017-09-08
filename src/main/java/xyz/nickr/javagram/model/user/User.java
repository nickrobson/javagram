package xyz.nickr.javagram.model.user;

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
public class User extends Model {

    private final long id;
    private final String firstName;
    private final Optional<String> lastName;
    private final Optional<String> username;
    private final Optional<String> languageCode;

    public User(TelegramBot bot, JSONObject user) {
        super(bot);
        this.id = user.getLong("id");
        this.firstName = user.getString("first_name");
        this.lastName = Optional.ofNullable(user.optString("last_name"));
        this.username = Optional.ofNullable(user.optString("username"));
        this.languageCode = Optional.ofNullable(user.optString("language_code"));
    }

    public static User from(TelegramBot bot, JSONObject user) {
        return user != null
                ? new User(bot, user)
                : null;
    }

}
