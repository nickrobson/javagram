package xyz.nickr.javagram.model.message.media;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.FileMessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StickerMessageContent extends FileMessageContent {

    private final Sticker sticker;

    public StickerMessageContent(TelegramBot bot, JSONObject sticker) {
        super(bot, MessageContentType.STICKER, sticker);
        this.sticker = new Sticker(bot, sticker, this.getFile());
    }

}
