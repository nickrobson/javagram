package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.InputFile;
import xyz.nickr.javagram.model.message.media.MaskPosition;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodCreateNewStickerSet extends Method<Boolean> {

    private long userId;
    private String name;
    private String title;
    private Object sticker;
    private String emojis;
    private Optional<Boolean> containsMasks = Optional.empty();
    private Optional<MaskPosition> maskPosition = Optional.empty();

    public MethodCreateNewStickerSet() {
        super("createNewStickerSet");
    }

    public MethodCreateNewStickerSet setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodCreateNewStickerSet setName(String name) {
        this.name = name;
        return this;
    }

    public MethodCreateNewStickerSet setTitle(String title) {
        this.title = title;
        return this;
    }

    public MethodCreateNewStickerSet setSticker(String fileIdOrUrl) {
        this.sticker = fileIdOrUrl;
        return this;
    }

    public MethodCreateNewStickerSet setSticker(InputFile sticker) {
        this.sticker = sticker;
        return this;
    }

    public MethodCreateNewStickerSet setEmojis(String emojis) {
        this.emojis = emojis;
        return this;
    }

    public MethodCreateNewStickerSet setContainsMasks(Boolean containsMasks) {
        this.containsMasks = Optional.ofNullable(containsMasks);
        return this;
    }

    public MethodCreateNewStickerSet setMaskPosition(MaskPosition maskPosition) {
        this.maskPosition = Optional.ofNullable(maskPosition);
        return this;
    }

    @Override
    public boolean isJSON() {
        return sticker instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        if (name == null)
            throw new InvalidMethodParameterException(this, "name");
        if (title == null)
            throw new InvalidMethodParameterException(this, "title");
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "png_sticker");
        if (emojis == null)
            throw new InvalidMethodParameterException(this, "emojis");
        String stickerString = (String) sticker;
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("name", name + "_by_" + bot.getBotUsername());
        json.put("title", title);
        json.put("png_sticker", stickerString);
        json.put("emojis", emojis);
        containsMasks.ifPresent(x -> json.put("contains_masks", x));
        maskPosition.ifPresent(x -> json.put("mask_position", x.toJSON()));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        if (name == null)
            throw new InvalidMethodParameterException(this, "name");
        if (title == null)
            throw new InvalidMethodParameterException(this, "title");
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "png_sticker");
        if (emojis == null)
            throw new InvalidMethodParameterException(this, "emojis");
        InputFile inputFile = (InputFile) sticker;
        MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
        multipart.addTextBody("user_id", String.valueOf(userId));
        multipart.addTextBody("name", name + "_by_" + bot.getBotUsername());
        multipart.addTextBody("title", title);
        multipart.addBinaryBody("png_sticker", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        multipart.addTextBody("emojis", emojis);
        containsMasks.ifPresent(x -> multipart.addTextBody("contains_masks", String.valueOf(x)));
        maskPosition.ifPresent(x -> multipart.addTextBody("mask_position", x.toJSON().toString()));
        return multipart;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
