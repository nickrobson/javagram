package xyz.nickr.javagram.method;

import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.message.media.StickerSet;

/**
 * @author Nick Robson
 */
public class MethodGetStickerSet extends Method<StickerSet> {

    private String name;

    public MethodGetStickerSet() {
        super("getStickerSet");
    }

    public MethodGetStickerSet setName(String name) {
        this.name = name;
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (name == null)
            throw new InvalidMethodParameterException(this, "name");
        json.put("name", name);
        return json;
    }

    @Override
    public StickerSet execute(TelegramBot bot) {
        JSONObject user = (JSONObject) bot.getExecutor().exec(this);
        return StickerSet.from(bot, user);
    }

}
