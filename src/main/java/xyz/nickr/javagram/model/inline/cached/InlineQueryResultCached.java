package xyz.nickr.javagram.model.inline.cached;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.inline.InlineQueryResult;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public abstract class InlineQueryResultCached<T extends InlineQueryResultCached<T>> extends InlineQueryResult {

    private final String fileIdField;
    private String fileId;

    public InlineQueryResultCached(TelegramBot bot, String type, String fileIdField) {
        super(bot, type);
        this.fileIdField = fileIdField;
    }

    @SuppressWarnings("unchecked")
    public T setFileId(String fileId) {
        this.fileId = fileId;
        return (T) this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = super.toJSON();
        json.put(fileIdField, fileId);
        return json;
    }

}
