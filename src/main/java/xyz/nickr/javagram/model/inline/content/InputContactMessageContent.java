package xyz.nickr.javagram.model.inline.content;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InputContactMessageContent extends InputMessageContent {

    private String phoneNumber;
    private String firstName;
    private Optional<String> lastName = Optional.empty();

    public InputContactMessageContent(TelegramBot bot) {
        super(bot);
    }

    public InputContactMessageContent setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public InputContactMessageContent setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public InputContactMessageContent setLastName(String lastName) {
        this.lastName = Optional.ofNullable(lastName);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (phoneNumber == null)
            throw new InvalidModelParameterException(this, "phone_number");
        if (firstName == null)
            throw new InvalidModelParameterException(this, "first_name");
        JSONObject json = new JSONObject();
        json.put("phone_number", phoneNumber);
        json.put("first_name", firstName);
        lastName.ifPresent(s -> json.put("last_name", s));
        return json;
    }

}
