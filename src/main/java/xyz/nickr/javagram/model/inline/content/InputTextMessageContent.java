package xyz.nickr.javagram.model.inline.content;

import java.util.Optional;
import lombok.Data;
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
public class InputTextMessageContent extends InputMessageContent {

    private String messageText;
    private Optional<String> parseMode = Optional.empty();
    private Optional<Boolean> disableWebPagePreview = Optional.empty();

    public InputTextMessageContent(TelegramBot bot) {
        super(bot);
    }

    public InputTextMessageContent setMessageText(String messageText) {
        this.messageText = messageText;
        return this;
    }

    public InputTextMessageContent setParseMode(String parseMode) {
        this.parseMode = Optional.ofNullable(parseMode);
        return this;
    }

    public InputTextMessageContent setDisableWebPagePreview(Boolean disableWebPagePreview) {
        this.disableWebPagePreview = Optional.ofNullable(disableWebPagePreview);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (messageText == null)
            throw new InvalidModelParameterException(this, "message_text");
        JSONObject json = new JSONObject();
        json.put("message_text", messageText);
        parseMode.ifPresent(s -> json.put("parse_mode", s));
        disableWebPagePreview.ifPresent(b -> json.put("disable_web_page_preview", b));
        return json;
    }

}
