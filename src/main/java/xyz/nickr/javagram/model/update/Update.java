package xyz.nickr.javagram.model.update;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidUpdateException;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Update extends Model {

    private final UpdateType type;
    private final long updateId;

    public Update(TelegramBot bot, UpdateType type, long updateId) {
        super(bot);
        this.type = type;
        this.updateId = updateId;
    }

    public final UpdateType getUpdateType() {
        return type;
    }

    public final long getUpdateId() {
        return this.updateId;
    }

    public static Update from(TelegramBot bot, JSONObject json) {
        if (!json.has("update_id")) {
            throw new InvalidUpdateException("Missing update_id", json);
        }
        long updateId = json.getLong("update_id");
        if (json.has("message")) {
            return new MessageUpdate(bot, updateId, json.getJSONObject("message"));
        } else if (json.has("edited_message")) {
            return new MessageEditedUpdate(bot, updateId, json.getJSONObject("edited_message"));
        } else if (json.has("channel_post")) {
            return new ChannelPostUpdate(bot, updateId, json.getJSONObject("channel_post"));
        } else if (json.has("edited_channel_post")) {
            return new ChannelPostEditedUpdate(bot, updateId, json.getJSONObject("edited_channel_post"));
        } else if (json.has("inline_query")) {
            return new InlineQueryUpdate(bot, updateId, json.getJSONObject("inline_query"));
        } else if (json.has("chosen_inline_result")) {
            return new InlineResultUpdate(bot, updateId, json.getJSONObject("chosen_inline_result"));
        } else if (json.has("callback_query")) {
            return new CallbackQueryUpdate(bot, updateId, json.getJSONObject("callback_query"));
        } else if (json.has("shipping_query")) {
            return new ShippingQueryUpdate(bot, updateId, json.getJSONObject("shipping_query"));
        } else if (json.has("pre_checkout_query")) {
            return new PreCheckoutQueryUpdate(bot, updateId, json.getJSONObject("pre_checkout_query"));
        }
        throw new InvalidUpdateException("Invalid update type", json);
    }

}
