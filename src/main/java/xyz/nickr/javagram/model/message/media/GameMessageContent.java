package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.PhotoSize;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GameMessageContent extends MessageContent {

    private final String title;
    private final String description;
    private final PhotoSize[] photoSizes;
    private final Optional<String> text;
    private final Optional<TextMessageEntity[]> textEntities;

    private final boolean hasAnimation;
    private final Optional<String> animationFileId;
    private final Optional<PhotoSize> animationThumb;
    private final Optional<String> animationFileName;
    private final Optional<String> animationMimeType;
    private final Optional<Long> animationFileSize;

    @Getter(AccessLevel.NONE) private Optional<ParsedText> parsed;

    public GameMessageContent(TelegramBot bot, JSONObject game) {
        super(bot, MessageContentType.GAME);
        this.title = game.getString("title");
        this.description = game.getString("description");

        JSONArray photos = game.getJSONArray("photo");
        this.photoSizes = new PhotoSize[photos.length()];
        for (int i = 0; i < photos.length(); i++)
            this.photoSizes[i] = new PhotoSize(bot, photos.getJSONObject(i));

        if (game.has("text")) {
            this.text = Optional.of(game.getString("text"));
            if (game.has("text_entities")) {
                JSONArray arr = game.getJSONArray("text_entities");
                TextMessageEntity[] textEntities = new TextMessageEntity[arr.length()];
                for (int i = 0; i < arr.length(); i++)
                    textEntities[i] = new TextMessageEntity(bot, arr.getJSONObject(i));
                this.textEntities = Optional.of(textEntities);
            } else {
                this.textEntities = Optional.of(new TextMessageEntity[0]);
            }
        } else {
            this.text = Optional.empty();
            this.textEntities = Optional.empty();
        }

        if (this.hasAnimation = game.has("animation")) {
            JSONObject animation = game.getJSONObject("animation");
            this.animationFileId = Optional.of(animation.getString("file_id"));
            this.animationThumb = Optional.ofNullable(PhotoSize.from(bot, animation.optJSONObject("thumb")));
            this.animationFileName = Optional.ofNullable(animation.optString("file_name"));
            this.animationMimeType = Optional.ofNullable(animation.optString("mime_type"));
            this.animationFileSize = Optional.ofNullable(animation.has("file_size") ? animation.getLong("file_size") : null);
        } else {
            this.animationFileId = Optional.empty();
            this.animationThumb = Optional.empty();
            this.animationFileName = Optional.empty();
            this.animationMimeType = Optional.empty();
            this.animationFileSize = Optional.empty();
        }
    }

    public Optional<ParsedText> parse() {
        return this.parsed != null
            ? this.parsed
            : (this.parsed = Optional.ofNullable(new ParsedText(this.text.get(), this.textEntities.get())));
    }

}
