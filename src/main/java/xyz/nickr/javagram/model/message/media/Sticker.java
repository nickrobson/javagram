package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.File;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.PhotoSize;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class Sticker extends Model {

    private final String fileId;
    private final File file;
    private final int width;
    private final int height;
    private final Optional<PhotoSize> thumb;
    private final Optional<String> emoji;
    private final Optional<String> setName; // name of the sticker set
    private final Optional<MaskPosition> maskPosition; // name of the sticker set
    private final Optional<Long> fileSize;

    public Sticker(TelegramBot bot, JSONObject sticker, File file) {
        super(bot);
        this.fileId = sticker.getString("file_id");
        this.file = file != null ? file : new File(bot, this.fileId);
        this.width = sticker.getInt("width");
        this.height = sticker.getInt("height");
        this.thumb = Optional.ofNullable(PhotoSize.from(bot, sticker.optJSONObject("thumb")));
        this.emoji = Optional.ofNullable(sticker.optString("emoji"));
        this.setName = Optional.ofNullable(sticker.optString("set_name"));
        this.maskPosition = Optional.ofNullable(MaskPosition.from(bot, sticker.optJSONObject("mask_position")));
        this.fileSize = Optional.ofNullable(sticker.has("file_size") ? sticker.getLong("file_size") : null);
    }

}
