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
public class MethodAddStickerToSet extends Method<Boolean> {

    private long userId;
    private String name;
    private Object sticker;
    private String emojis;
    private Optional<MaskPosition> maskPosition = Optional.empty();

    public MethodAddStickerToSet() {
        super("addStickerToSet");
    }

    public MethodAddStickerToSet setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodAddStickerToSet setName(String name) {
        this.name = name;
        return this;
    }

    public MethodAddStickerToSet setSticker(String fileIdOrUrl) {
        this.sticker = fileIdOrUrl;
        return this;
    }

    public MethodAddStickerToSet setSticker(InputFile sticker) {
        this.sticker = sticker;
        return this;
    }

    public MethodAddStickerToSet setEmojis(String emojis) {
        this.emojis = emojis;
        return this;
    }

    public MethodAddStickerToSet setMaskPosition(MaskPosition maskPosition) {
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
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "png_sticker");
        if (emojis == null)
            throw new InvalidMethodParameterException(this, "emojis");
        String stickerString = (String) sticker;
        JSONObject json = new JSONObject();
        json.put("user_id", userId);
        json.put("name", name);
        json.put("png_sticker", stickerString);
        json.put("emojis", emojis);
        maskPosition.ifPresent(x -> json.put("mask_position", x.toJSON()));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        if (name == null)
            throw new InvalidMethodParameterException(this, "name");
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "png_sticker");
        if (emojis == null)
            throw new InvalidMethodParameterException(this, "emojis");
        InputFile inputFile = (InputFile) sticker;
        MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
        multipart.addTextBody("user_id", String.valueOf(userId));
        multipart.addTextBody("name", name);
        multipart.addBinaryBody("png_sticker", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        multipart.addTextBody("emojis", emojis);
        maskPosition.ifPresent(x -> multipart.addTextBody("mask_position", x.toJSON().toString()));
        return multipart;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
