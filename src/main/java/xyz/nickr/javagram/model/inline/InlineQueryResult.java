package xyz.nickr.javagram.model.inline;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.inline.content.InputMessageContent;
import xyz.nickr.javagram.model.replymarkup.InlineKeyboardMarkup;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class InlineQueryResult<T extends InlineQueryResult<T>> extends Model {

    private final String type;
    private String id;
    private Optional<InlineKeyboardMarkup> replyMarkup = Optional.empty();
    protected Optional<InputMessageContent> messageContent = Optional.empty();

    public InlineQueryResult(TelegramBot bot, String type) {
        super(bot);
        this.type = type;
    }

    @SuppressWarnings("unchecked")
    public T setId(String id) {
        this.id = id;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = Optional.ofNullable(replyMarkup);
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T setMessageContent(InputMessageContent messageContent) {
        this.messageContent = Optional.ofNullable(messageContent);
        return (T) this;
    }

    public JSONObject toJSON() {
        if (type == null)
            throw new InvalidModelParameterException(this, "type");
        if (id == null)
            throw new InvalidModelParameterException(this, "id");
        JSONObject json = new JSONObject();
        json.put("type", type);
        json.put("id", id);
        replyMarkup.ifPresent(m -> json.put("reply_markup", m.toJSON()));
        if (messageContent != null)
            messageContent.ifPresent(m -> json.put("input_message_content", m.toJSON()));
        return json;
    }

}
