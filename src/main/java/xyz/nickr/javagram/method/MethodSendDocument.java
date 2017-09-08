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
public class MethodSendDocument extends Method.MethodSend<MethodSendDocument> {

    private Object document;
    private Optional<String> caption = Optional.empty();

    public MethodSendDocument() {
        super("sendDocument");
    }

    public MethodSendDocument setDocument(String fileIdOrUrl) {
        this.document = fileIdOrUrl;
        return this;
    }

    public MethodSendDocument setDocument(InputFile inputFile) {
        this.document = inputFile;
        return this;
    }

    public MethodSendDocument setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    @Override
    public boolean isJSON() {
        return document instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (document == null)
            throw new InvalidMethodParameterException(this, "document");
        json.put("document", document.toString());
        caption.ifPresent(s -> json.put("caption", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) document;
        if (document == null)
            throw new InvalidMethodParameterException(this, "document");
        multipart.addBinaryBody("document", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        caption.ifPresent(s -> multipart.addTextBody("caption", s));
        return multipart;
    }

}
