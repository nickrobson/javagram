package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.File;
import xyz.nickr.javagram.model.chat.Chat;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodSetStickerPositionInSet extends Method<Boolean> {

    private String fileId;
    private int position;

    public MethodSetStickerPositionInSet() {
        super("setStickerPositionInSet");
    }

    public MethodSetStickerPositionInSet setFile(File file) {
        this.fileId = file.getFileId();
        return this;
    }

    public MethodSetStickerPositionInSet setFileId(String fileId) {
        this.fileId = fileId;
        return this;
    }

    public MethodSetStickerPositionInSet setPosition(int position) {
        this.position = position;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (fileId == null)
            throw new InvalidMethodParameterException(this, "sticker");
        if (position < 0)
            throw new InvalidMethodParameterException(this, "position", ">= 0");
        json.put("sticker", fileId);
        json.put("position", position);
        return json;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
