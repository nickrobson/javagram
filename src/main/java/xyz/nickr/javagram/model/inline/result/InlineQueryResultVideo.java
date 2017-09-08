package xyz.nickr.javagram.model.inline.result;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.inline.InlineQueryResult;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryResultVideo extends InlineQueryResult<InlineQueryResultVideo> {

    private String videoUrl;
    private String mimeType;
    private String thumbUrl;
    private String title;
    private Optional<String> caption = Optional.empty();
    private Optional<Integer> videoWidth = Optional.empty();
    private Optional<Integer> videoHeight = Optional.empty();
    private Optional<Integer> videoDuration = Optional.empty();
    private Optional<String> description = Optional.empty();

    public InlineQueryResultVideo(TelegramBot bot) {
        super(bot, "video");
    }

    public InlineQueryResultVideo setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public InlineQueryResultVideo setMimeType(String mimeType) {
        this.mimeType = mimeType;
        return this;
    }

    public InlineQueryResultVideo setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
        return this;
    }

    public InlineQueryResultVideo setTitle(String title) {
        this.title = title;
        return this;
    }

    public InlineQueryResultVideo setCaption(String caption) {
        this.caption = Optional.ofNullable(caption);
        return this;
    }

    public InlineQueryResultVideo setVideoWidth(Integer videoWidth) {
        this.videoWidth = Optional.ofNullable(videoWidth);
        return this;
    }

    public InlineQueryResultVideo setVideoHeight(Integer videoHeight) {
        this.videoHeight = Optional.ofNullable(videoHeight);
        return this;
    }

    public InlineQueryResultVideo setVideoDuration(Integer videoDuration) {
        this.videoDuration = Optional.ofNullable(videoDuration);
        return this;
    }

    public InlineQueryResultVideo setDescription(String description) {
        this.description = Optional.ofNullable(description);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        if (videoUrl == null)
            throw new InvalidModelParameterException(this, "video_url");
        if (mimeType == null)
            throw new InvalidModelParameterException(this, "mime_type");
        if (thumbUrl == null)
            throw new InvalidModelParameterException(this, "thumb_url");
        if (title == null)
            throw new InvalidModelParameterException(this, "title");
        JSONObject json = super.toJSON();
        json.put("video_url", videoUrl);
        json.put("mime_type", mimeType);
        json.put("thumb_url", thumbUrl);
        json.put("title", title);
        caption.ifPresent(x -> json.put("caption", x));
        videoWidth.ifPresent(x -> json.put("video_width", x));
        videoHeight.ifPresent(x -> json.put("video_height", x));
        videoDuration.ifPresent(x -> json.put("video_duration", x));
        description.ifPresent(x -> json.put("description", x));
        return json;
    }
}
