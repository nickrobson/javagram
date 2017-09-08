package xyz.nickr.javagram.model.message;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Message extends Model {

    private final long messageId;
    private final Optional<User> sender;
    private final Chat chat;

    private final LocalDateTime timestamp;
    private final Optional<LocalDateTime> editTimestamp;

    private final Optional<User> forwardedFrom;
    private final Optional<Chat> forwardedFromChat;
    private final Optional<Integer> forwardedFromMessageId;
    private final Optional<LocalDateTime> forwardedMessageDate;

    private final Optional<Message> replyToMessage;

    private final MessageContent content;

    public Message(TelegramBot bot, JSONObject message) {
        super(bot);
        this.messageId = message.getLong("message_id");
        this.sender = Optional.ofNullable(User.from(bot, message.optJSONObject("from")));
        this.chat = Chat.from(bot, message.getJSONObject("chat"));
        this.timestamp = LocalDateTime.ofInstant(Instant.ofEpochSecond(message.getLong("date")), ZoneOffset.UTC);
        this.editTimestamp = Optional.ofNullable(message.has("edit_date") ? LocalDateTime.ofInstant(Instant.ofEpochSecond(message.getLong("edit_date")), ZoneOffset.UTC) : null);
        this.forwardedFrom = Optional.ofNullable(User.from(bot, message.optJSONObject("forward_from")));
        this.forwardedFromChat = Optional.ofNullable(Chat.from(bot, message.optJSONObject("forward_from_chat")));
        this.forwardedFromMessageId = Optional.ofNullable(message.has("forward_from_message_id") ? message.getInt("forward_from_message_id") : null);
        this.forwardedMessageDate = Optional.ofNullable(message.has("forward_date") ? LocalDateTime.ofInstant(Instant.ofEpochSecond(message.getLong("forward_date")), ZoneOffset.UTC) : null);
        this.replyToMessage = Optional.ofNullable(Message.from(bot, message.optJSONObject("reply_to_message")));
        this.content = MessageContent.from(bot, message);
    }

    public static Message from(TelegramBot bot, JSONObject message) {
        return message != null
                ? new Message(bot, message)
                : null;
    }

}
