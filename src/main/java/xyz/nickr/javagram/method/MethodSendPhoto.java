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

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendPhoto extends Method.MethodSend<MethodSendPhoto> {

    private Object photo;
    private Optional<String> caption = Optional.empty();

    public MethodSendPhoto() {
        super("sendPhoto");
    }

    public MethodSendPhoto setPhoto(String fileIdOrUrl) {
        this.photo = fileIdOrUrl;
        return this;
    }

    public MethodSendPhoto setPhoto(InputFile inputFile) {
        this.photo = inputFile;
        return this;
    }

    public MethodSendPhoto setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public boolean isJSON() {
        return photo instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (photo == null)
            throw new InvalidMethodParameterException(this, "photo");
        json.put("photo", photo.toString());
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) photo;
        if (photo == null)
            throw new InvalidMethodParameterException(this, "photo");
        multipart.addBinaryBody("photo", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        caption.ifPresent(s -> multipart.addTextBody("caption", s));
        return multipart;
    }

}
