package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.File;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodDeleteStickerFromSet extends Method<Boolean> {

    private String fileId;

    public MethodDeleteStickerFromSet() {
        super("deleteStickerFromSet");
    }

    public MethodDeleteStickerFromSet setFile(File file) {
        this.fileId = file.getFileId();
        return this;
    }

    public MethodDeleteStickerFromSet setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (fileId == null)
            throw new InvalidMethodParameterException(this, "sticker");
        json.put("sticker", fileId);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
