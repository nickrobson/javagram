package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ContactMessageContent extends MessageContent {

    private final String phoneNumber;
    private final String firstName;
    private final Optional<String> lastName;
    private final Optional<Long> userId;

    public ContactMessageContent(TelegramBot bot, JSONObject contact) {
        super(bot, MessageContentType.CONTACT);
        this.phoneNumber = contact.getString("phone_number");
        this.firstName = contact.getString("first_name");
        this.lastName = Optional.ofNullable(contact.optString("last_name"));
        this.userId = Optional.ofNullable(contact.has("user_id") ? contact.getLong("user_id") : null);
    }

}
