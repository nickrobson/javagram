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
public class MethodSendAudio extends Method.MethodSend<MethodSendAudio> {

    private Object audio;
    private Optional<String> caption = Optional.empty();
    private Optional<Integer> duration = Optional.empty();
    private Optional<String> performer = Optional.empty();
    private Optional<String> title = Optional.empty();

    public MethodSendAudio() {
        super("sendAudio");
    }

    public MethodSendAudio setAudio(String fileIdOrUrl) {
        this.audio = fileIdOrUrl;
        return this;
    }

    public MethodSendAudio setAudio(InputFile inputFile) {
        this.audio = inputFile;
        return this;
    }

    public MethodSendAudio setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public MethodSendAudio setDuration(Integer duration) {
        this.duration = Optional.ofNullable(duration);
        return this;
    }

    public MethodSendAudio setPerformer(String performer) {
        this.performer = Optional.ofNullable(performer);
        return this;
    }

    public MethodSendAudio setTitle(String title) {
        this.title = Optional.ofNullable(title);
        return this;
    }

    @Override
    public boolean isJSON() {
        return audio instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (audio == null)
            throw new InvalidMethodParameterException(this, "audio");
        json.put("audio", audio.toString());
        caption.ifPresent(s -> json.put("caption", s));
        duration.ifPresent(s -> json.put("duration", s));
        performer.ifPresent(s -> json.put("performer", s));
        title.ifPresent(s -> json.put("title", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) audio;
        if (audio == null)
            throw new InvalidMethodParameterException(this, "audio");
        multipart.addBinaryBody("audio", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        caption.ifPresent(s -> multipart.addTextBody("caption", s));
        duration.ifPresent(s -> multipart.addTextBody("duration", s.toString()));
        performer.ifPresent(s -> multipart.addTextBody("performer", s));
        title.ifPresent(s -> multipart.addTextBody("title", s));
        return multipart;
    }

}
