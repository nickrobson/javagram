package xyz.nickr.javagram.model.chat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class Chat extends Model {

    private final long id;
    private final ChatType type;

    public Chat(TelegramBot bot, JSONObject chat, ChatType expected) {
        super(bot);
        this.id = chat.getLong("id");
        this.type = ChatType.valueOf(chat.getString("type").toUpperCase());

        if (this.type != expected) {
            throw new IllegalArgumentException("Wrong chat type!");
        }
    }

    public static Chat from(TelegramBot bot, JSONObject chat) {
        if (chat == null)
            return null;
        try {
            ChatType type = ChatType.valueOf(chat.getString("type").toUpperCase());
            switch (type) {
                case PRIVATE:
                    return new PrivateChat(bot, chat);
                case GROUP:
                    return new GroupChat(bot, chat);
                case SUPERGROUP:
                    return new SuperGroupChat(bot, chat);
                case CHANNEL:
                    return new ChannelChat(bot, chat);
            }
            throw new IllegalArgumentException("No Chat object type corresponding to " + type + "\n\t" + chat);
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid chat object!\n\t" + chat, ex);
        }
    }

}
