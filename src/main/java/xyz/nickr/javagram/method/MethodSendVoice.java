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
public class MethodSendVoice extends Method.MethodSend<MethodSendVoice> {

    private Object voice;
    private Optional<String> caption = Optional.empty();
    private Optional<Integer> duration = Optional.empty();

    public MethodSendVoice() {
        super("sendVoice");
    }

    public MethodSendVoice setVoice(String fileIdOrUrl) {
        this.voice = fileIdOrUrl;
        return this;
    }

    public MethodSendVoice setVoice(InputFile inputFile) {
        this.voice = inputFile;
        return this;
    }

    public MethodSendVoice setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public MethodSendVoice setDuration(Integer duration) {
        this.duration = Optional.ofNullable(duration);
        return this;
    }

    @Override
    public boolean isJSON() {
        return voice instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (voice == null)
            throw new InvalidMethodParameterException(this, "voice");
        json.put("voice", voice.toString());
        caption.ifPresent(s -> json.put("caption", s));
        duration.ifPresent(s -> json.put("duration", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) voice;
        if (voice == null)
            throw new InvalidMethodParameterException(this, "voice");
        multipart.addBinaryBody("voice", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        caption.ifPresent(s -> multipart.addTextBody("caption", s));
        duration.ifPresent(s -> multipart.addTextBody("duration", s.toString()));
        return multipart;
    }

}
