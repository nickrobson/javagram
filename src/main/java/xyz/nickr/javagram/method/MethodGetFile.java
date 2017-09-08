package xyz.nickr.javagram.method;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.File;

/**
 * @author Nick Robson
 */
@Getter
@Accessors(chain = true)
public class MethodGetFile extends Method<File> {

    @Setter private String fileId;

    public MethodGetFile() {
        super("getFile");
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (fileId == null)
            throw new InvalidMethodParameterException(this, "file_id");
        json.put("file_id", this.fileId);
        return json;
    }

    @Override
    public File execute(TelegramBot bot) {
        JSONObject message = (JSONObject) bot.getExecutor().exec(this);
        return new File(bot, message);
    }

}
