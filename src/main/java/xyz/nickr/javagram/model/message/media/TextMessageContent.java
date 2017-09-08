package xyz.nickr.javagram.model.message.media;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true, exclude = {"parsed", "command"})
@EqualsAndHashCode(callSuper = true, exclude = "parsed")
public class TextMessageContent extends MessageContent {

    private final String text;
    private final TextMessageEntity[] entities;

    @Getter(AccessLevel.NONE) private ParsedText parsed;
    @Getter(AccessLevel.NONE) private String[] command;

    public TextMessageContent(TelegramBot bot, JSONObject message) {
        super(bot, MessageContentType.TEXT);
        this.text = message.getString("text");
        JSONArray arr = message.optJSONArray("entities");
        if (arr != null) {
            this.entities = new TextMessageEntity[arr.length()];
            for (int i = 0; i < arr.length(); i++) {
                this.entities[i] = new TextMessageEntity(bot, arr.getJSONObject(i));
            }
        } else {
            this.entities = new TextMessageEntity[0];
        }
    }

    public boolean isCommand() {
        if (command != null)
            return true;
        if (entities.length == 0)
            return false;
        TextMessageEntity firstEntity = entities[0];
        return firstEntity.getOffset() == 0 && firstEntity.getType() == TextMessageEntityType.BOT_COMMAND;
    }

    /**
     * Gets the command name and the bot username as two elements in a String[].
     * <br/>
     *     <b>Note</b>: If no {@code @username} was provided, the second element will be {@code null}.
     * @return The command name and the bot username
     */
    public String[] getCommand() {
        if (command != null)
            return command;
        if (!isCommand())
            throw new IllegalStateException("not a command - use isCommand() first");
        TextMessageEntity firstEntity = entities[0];
        String content = text.substring(firstEntity.getOffset() + 1, firstEntity.getOffset() + firstEntity.getLength());
        String[] split = content.split("@");
        return command = Arrays.copyOf(split, 2);
    }

    public ParsedText parse() {
        return this.parsed != null
                ? this.parsed
                : (this.parsed = new ParsedText(this.text, this.entities));
    }

}
