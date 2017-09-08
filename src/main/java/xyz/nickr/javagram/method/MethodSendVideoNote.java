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
public class MethodSendVideoNote extends Method.MethodSend<MethodSendVideoNote> {

    private Object videoNote;
    private Optional<Integer> duration = Optional.empty();
    private Optional<Integer> length = Optional.empty();

    public MethodSendVideoNote() {
        super("sendVideoNote");
    }

    public MethodSendVideoNote setVideoNote(String fileIdOrUrl) {
        this.videoNote = fileIdOrUrl;
        return this;
    }

    public MethodSendVideoNote setVideoNote(InputFile inputFile) {
        this.videoNote = inputFile;
        return this;
    }

    public MethodSendVideoNote setDuration(Integer duration) {
        this.duration = Optional.ofNullable(duration);
        return this;
    }

    public MethodSendVideoNote setLength(Integer width) {
        this.length = Optional.ofNullable(width);
        return this;
    }

    @Override
    public boolean isJSON() {
        return videoNote instanceof String;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = super.toJSON(bot);
        if (videoNote == null)
            throw new InvalidMethodParameterException(this, "video_note");
        json.put("video_note", videoNote.toString());
        duration.ifPresent(s -> json.put("duration", s));
        length.ifPresent(s -> json.put("length", s));
        return json;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = super.toMultipart(bot);
        InputFile inputFile = (InputFile) videoNote;
        if (videoNote == null)
            throw new InvalidMethodParameterException(this, "video_note");
        multipart.addBinaryBody("video_note", inputFile.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, inputFile.getName());
        duration.ifPresent(s -> multipart.addTextBody("duration", s.toString()));
        length.ifPresent(s -> multipart.addTextBody("length", s.toString()));
        return multipart;
    }

}
