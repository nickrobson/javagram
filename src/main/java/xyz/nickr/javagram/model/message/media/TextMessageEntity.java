package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
public class TextMessageEntity {

    private final TextMessageEntityType type;
    private final int offset;
    private final int length;
    private final Optional<String> url;
    private final Optional<User> user;

    public TextMessageEntity(TextMessageEntityType type, int offset, int length) {
        this.type = type;
        this.offset = offset;
        this.length = length;
        this.url = Optional.empty();
        this.user = Optional.empty();
    }

    public TextMessageEntity(TelegramBot bot, JSONObject entity) {
        this.type = TextMessageEntityType.valueOf(entity.getString("type").toUpperCase());
        this.offset = entity.getInt("offset");
        this.length = entity.getInt("length");
        if (entity.has("url")) {
            this.url = Optional.of(entity.getString("url"));
        } else {
            this.url = Optional.empty();
        }
        if (entity.has("user")) {
            this.user = Optional.of(new User(bot, entity.getJSONObject("user")));
        } else {
            this.user = Optional.empty();
        }
    }

}
