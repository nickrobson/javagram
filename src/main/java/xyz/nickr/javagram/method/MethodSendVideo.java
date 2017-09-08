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
public class MethodSendVideo extends Method.MethodSend<MethodSendVideo> {

    private Object video;
    private Optional<String> caption = Optional.empty();
    private Optional<Integer> duration = Optional.empty();
    private Optional<Integer> width = Optional.empty();
    private Optional<Integer> height = Optional.empty();

    public MethodSendVideo() {
        super("sendVideo");
    }

    public MethodSendVideo setVideo(String fileIdOrUrl) {
        this.video = fileIdOrUrl;
        return this;
    }

    public MethodSendVideo setVideo(InputFile inputFile) {
        this.video = inputFile;
        return this;
    }

    public MethodSendVideo setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public MethodSendVideo setDuration(Integer duration) {
        this.duration = Optional.ofNullable(duration);
        return this;
    }

    public MethodSendVideo setWidth(Integer width) {
        this.width = Optional.ofNullable(width);
        return this;
    }

    public MethodSendVideo setHeight(Integer height) {
        this.height = Optional.ofNullable(height);
        return this;
    }

    @Override
    public boolean isJSON() {
        return video instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (video == null)
            throw new InvalidMethodParameterException(this, "video");
        json.put("video", video.toString());
        caption.ifPresent(s -> json.put("caption", s));
        duration.ifPresent(s -> json.put("duration", s));
        width.ifPresent(s -> json.put("width", s));
        height.ifPresent(s -> json.put("height", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) video;
        if (video == null)
            throw new InvalidMethodParameterException(this, "video");
        multipart.addBinaryBody("video", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        caption.ifPresent(s -> multipart.addTextBody("caption", s));
        duration.ifPresent(s -> multipart.addTextBody("duration", s.toString()));
        width.ifPresent(s -> multipart.addTextBody("width", s.toString()));
        height.ifPresent(s -> multipart.addTextBody("height", s.toString()));
        return multipart;
    }

}
