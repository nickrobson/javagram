package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.update.Update;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class MethodGetUpdates extends Method<Update[]> {

    private Optional<Long> offset = Optional.empty();
    private Optional<Integer> limit = Optional.empty();
    private Optional<Integer> timeout = Optional.empty();
    private Optional<String[]> allowedUpdates = Optional.empty();

    public MethodGetUpdates() {
        super("getUpdates");
    }

    public MethodGetUpdates setOffset(long offset) {
        return setOffset((Long) offset);
    }

    public MethodGetUpdates setOffset(Long offset) {
        this.offset = Optional.ofNullable(offset);
        return this;
    }

    public MethodGetUpdates setLimit(Integer limit) {
        this.limit = Optional.ofNullable(limit);
        return this;
    }

    public MethodGetUpdates setTimeout(Integer timeout) {
        this.timeout = Optional.ofNullable(timeout);
        return this;
    }

    public MethodGetUpdates setAllowedUpdates(String[] allowedUpdates) {
        this.allowedUpdates = Optional.ofNullable(allowedUpdates);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        offset.ifPresent(i -> json.put("offset", i));
        limit.ifPresent(i -> json.put("limit", i));
        json.put("timeout", timeout.orElse(10));
        allowedUpdates.ifPresent(i -> json.put("allowed_updates", allowedUpdates));
        return json;
    }

    @Override
    public Update[] execute(TelegramBot bot) {
        JSONArray arr = (JSONArray) bot.getExecutor().exec(this);
        Update[] updates = new Update[arr.length()];
        for (int i = 0; i < arr.length(); i++) {
            updates[i] = Update.from(bot, arr.getJSONObject(i));
        }
        return updates;
    }

}
