package xyz.nickr.javagram.model.message.media;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class StickerSet extends Model {

    private final String name;
    private final String title;
    private final boolean containsMasks;
    private final Sticker[] stickers;

    public StickerSet(TelegramBot bot, JSONObject sticker_set) {
        super(bot);
        this.name = sticker_set.getString("name");
        this.title = sticker_set.getString("title");
        this.containsMasks = sticker_set.getBoolean("contains_masks");

        JSONArray stickers = sticker_set.getJSONArray("stickers");
        this.stickers = new Sticker[stickers.length()];
        for (int i = 0, j = stickers.length(); i < j; i++) {
            this.stickers[i] = new Sticker(bot, stickers.getJSONObject(i), null);
        }
    }

    public static StickerSet from(TelegramBot bot, JSONObject sticker_set) {
        return sticker_set != null
                ? new StickerSet(bot, sticker_set)
                : null;
    }
}
