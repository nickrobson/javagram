package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.File;
import xyz.nickr.javagram.model.InputFile;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodUploadStickerFile extends Method<File> {

    private long userId;
    private InputFile sticker;

    public MethodUploadStickerFile() {
        super("uploadStickerFile");
    }

    public MethodUploadStickerFile setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodUploadStickerFile setSticker(InputFile sticker) {
        this.sticker = sticker;
        return this;
    }

    @Override
    public boolean isJSON() {
        return false;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "png_sticker");
        multipart.addTextBody("user_id", String.valueOf(userId));
        multipart.addBinaryBody("png_sticker", sticker.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, sticker.getName());
        return multipart;
    }

    @Override
    public File execute(TelegramBot bot) {
        JSONObject json = (JSONObject) bot.getExecutor().exec(this);
        return new File(bot, json);
    }

}
