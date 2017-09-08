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
public class MethodSendContact extends Method.MethodSend<MethodSendContact> {

    private String phoneNumber;
    private String firstName;
    private Optional<String> lastName = Optional.empty();

    public MethodSendContact() {
        super("sendContact");
    }

    public MethodSendContact setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public MethodSendContact setFirstName(long longitude) {
        this.firstName = firstName;
        return this;
    }

    public MethodSendContact setLastName(String lastName) {
        this.lastName = Optional.ofNullable(lastName);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (phoneNumber == null)
            throw new InvalidMethodParameterException(this, "phone_number");
        if (firstName == null)
            throw new InvalidMethodParameterException(this, "first_name");
        json.put("phone_number", phoneNumber);
        json.put("first_name", firstName);
        lastName.ifPresent(s -> json.put("last_name", s));
        return json;
    }

}
