package xyz.nickr.javagram.model.replymarkup;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ForceReply extends ReplyMarkup {

    private Optional<Boolean> selective = Optional.empty();

    public ForceReply(TelegramBot bot) {
        super(bot);
    }

    public ForceReply setSelective(Boolean selective) {
        this.selective = Optional.ofNullable(selective);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("force_reply", true);
        selective.ifPresent(b -> json.put("selective", b));
        return json;
    }

}
