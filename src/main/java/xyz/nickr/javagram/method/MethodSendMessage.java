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
public class MethodSendMessage extends Method.MethodSend<MethodSendMessage> {

    private String text;
    private Optional<String> parseMode = Optional.empty();
    private Optional<Boolean> disableWebPreview = Optional.empty();

    public MethodSendMessage() {
        super("sendMessage");
    }

    public MethodSendMessage setText(String text) {
        this.text = text;
        return this;
    }

    public MethodSendMessage setParseMode(String parseMode) {
        this.parseMode = Optional.ofNullable(parseMode);
        return this;
    }

    public MethodSendMessage setDisableWebPreview(Boolean disableWebPreview) {
        this.disableWebPreview = Optional.ofNullable(disableWebPreview);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (text == null)
            throw new InvalidMethodParameterException(this, "text");
        json.put("text", text);
        parseMode.ifPresent(s -> json.put("parse_mode", s));
        disableWebPreview.ifPresent(b -> json.put("disable_web_page_preview", b));
        return json;
    }

}
