package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.InputFile;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendSticker extends Method.MethodSend<MethodSendSticker> {

    private Object sticker;

    public MethodSendSticker() {
        super("sendSticker");
    }

    public MethodSendSticker setSticker(String fileIdOrUrl) {
        this.sticker = fileIdOrUrl;
        return this;
    }

    public MethodSendSticker setSticker(InputFile inputFile) {
        this.sticker = inputFile;
        return this;
    }

    @Override
    public boolean isJSON() {
        return sticker instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "sticker");
        json.put("sticker", sticker.toString());
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) sticker;
        if (sticker == null)
            throw new InvalidMethodParameterException(this, "sticker");
        multipart.addBinaryBody("sticker", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        return multipart;
    }

}
