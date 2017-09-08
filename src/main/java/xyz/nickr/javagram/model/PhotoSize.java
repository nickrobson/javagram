package xyz.nickr.javagram.model;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class PhotoSize extends Model {

    private final String fileId;
    private final int width;
    private final int height;
    private final Optional<Long> fileSize;
    private final File file;

    public PhotoSize(TelegramBot bot, JSONObject photo_size) {
        super(bot);
        this.fileId = photo_size.getString("file_id");
        this.width = photo_size.getInt("width");
        this.height = photo_size.getInt("height");
        this.fileSize = Optional.ofNullable(photo_size.has("file_size") ? photo_size.getLong("file_size") : null);
        this.file = new File(bot, this.fileId);
    }

    public File asFile() {
        return file;
    }

    public static PhotoSize from(TelegramBot bot, JSONObject photo_size) {
        return photo_size != null
                ? new PhotoSize(bot, photo_size)
                : null;
    }

}
