package xyz.nickr.javagram.model.inline.result;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.inline.InlineQueryResult;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryResultContact extends InlineQueryResult<InlineQueryResultContact> {

    private String phoneNumber;
    private String firstName;
    private Optional<String> lastName = Optional.empty();
    private Optional<String> thumbUrl = Optional.empty();
    private Optional<Integer> thumbWidth = Optional.empty();
    private Optional<Integer> thumbHeight = Optional.empty();

    public InlineQueryResultContact(TelegramBot bot) {
        super(bot, "contact");
    }

    public InlineQueryResultContact setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public InlineQueryResultContact setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public InlineQueryResultContact setLastName(String lastName) {
        this.lastName = Optional.ofNullable(lastName);
        return this;
    }

    public InlineQueryResultContact setThumbUrl(String thumbUrl) {
        this.thumbUrl = Optional.ofNullable(thumbUrl);
        return this;
    }

    public InlineQueryResultContact setThumbWidth(Integer thumbWidth) {
        this.thumbWidth = Optional.ofNullable(thumbWidth);
        return this;
    }

    public InlineQueryResultContact setThumbHeight(Integer thumbHeight) {
        this.thumbHeight = Optional.ofNullable(thumbHeight);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (phoneNumber == null)
            throw new InvalidModelParameterException(this, "phone_number");
        if (firstName == null)
            throw new InvalidModelParameterException(this, "first_name");
        JSONObject json = super.toJSON();
        json.put("phone_number", phoneNumber);
        json.put("first_name", firstName);
        lastName.ifPresent(x -> json.put("last_name", x));
        thumbUrl.ifPresent(x -> json.put("thumb_url", x));
        thumbWidth.ifPresent(x -> json.put("thumb_width", x));
        thumbHeight.ifPresent(x -> json.put("thumb_height", x));
        return json;
    }
}
