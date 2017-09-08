package xyz.nickr.javagram.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.File;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class FileMessageContent extends MessageContent {

    private final String fileId;
    private final File file;

    public FileMessageContent(TelegramBot bot, MessageContentType type, JSONObject content) {
        super(bot, type);
        this.fileId = content.getString("file_id");
        this.file = new File(bot, this.fileId);
    }

}
