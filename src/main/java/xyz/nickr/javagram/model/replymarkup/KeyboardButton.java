package xyz.nickr.javagram.model.replymarkup;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class KeyboardButton extends Model {

    private String text;
    private Optional<Boolean> requestContact = Optional.empty();
    private Optional<Boolean> requestLocation = Optional.empty();

    public KeyboardButton(TelegramBot bot) {
        super(bot);
    }

    public KeyboardButton setText(String text) {
        this.text = text;
        return this;
    }

    public KeyboardButton setRequestContact(Boolean requestContact) {
        this.requestContact = Optional.ofNullable(requestContact);
        return this;
    }

    public KeyboardButton setRequestLocation(Boolean requestLocation) {
        this.requestLocation = Optional.ofNullable(requestLocation);
        return this;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        if (text == null)
            throw new InvalidModelParameterException(this, "text");
        json.put("text", text);
        requestContact.ifPresent(b -> json.put("request_contact", b));
        requestLocation.ifPresent(b -> json.put("request_location", b));
        return json;
    }

}
