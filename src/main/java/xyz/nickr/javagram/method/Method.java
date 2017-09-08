package xyz.nickr.javagram.method;

import java.util.Optional;
import java.util.regex.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.ChannelChat;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.chat.ChatType;
import xyz.nickr.javagram.model.chat.SuperGroupChat;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.replymarkup.ReplyMarkup;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class Method<T> {

    private static final Pattern ROUTE_NAME_PATTERN = Pattern.compile("[a-z]+(?:[A-Z][a-z]*)*");

    private final String route;
    private boolean isJSON = true;

    public Method(String route) {
        if (route == null || !ROUTE_NAME_PATTERN.matcher(route).matches()) {
            throw new IllegalArgumentException("invalid route name");
        }
        this.route = route;
    }

    protected final Object getChatId(Chat chat) {
        Object chatId = null;
        if (chat.getType() == ChatType.CHANNEL) {
            chatId = ((ChannelChat) chat).getUsername().orElse(null);
        } else if (chat.getType() == ChatType.SUPERGROUP) {
            chatId = ((SuperGroupChat) chat).getUsername().orElse(null);
        }
        return chatId != null ? chatId : chat.getId();
    }

    public abstract T execute(TelegramBot bot);

    public JSONObject toJSON(TelegramBot bot) { throw new UnsupportedOperationException("toJSON() is not supported"); }
    public MultipartEntityBuilder toMultipart(TelegramBot bot) { throw new UnsupportedOperationException("toMultipart() is not supported"); }

    @Getter
    @ToString(callSuper = true)
    @SuppressWarnings("unchecked")
    @EqualsAndHashCode(callSuper = true)
    public static class MethodSend<T extends MethodSend> extends Method<Message> {

        private Object chatId;
        private Optional<Boolean> disableNotification = Optional.empty();
        private Optional<Long> replyToMessageId = Optional.empty();
        private Optional<ReplyMarkup> replyMarkup = Optional.empty();

        public MethodSend(String route) {
            super(route);
        }

        public T setChat(Chat chat) {
            this.chatId = this.getChatId(chat);
            return (T) this;
        }

        public T setChatId(long chatId) {
            this.chatId = chatId;
            return (T) this;
        }

        public T setChatId(String chatId) {
            this.chatId = chatId;
            return (T) this;
        }

        public T setDisableNotification(Boolean disableNotification) {
            this.disableNotification = Optional.ofNullable(disableNotification);
            return (T) this;
        }

        public T setReplyToMessageId(Long messageId) {
            this.replyToMessageId = Optional.ofNullable(messageId);
            return (T) this;
        }

        public T setReplyMarkup(ReplyMarkup replyMarkup) {
            this.replyMarkup = Optional.ofNullable(replyMarkup);
            return (T) this;
        }

        @Override
        public JSONObject toJSON(TelegramBot bot) {
            JSONObject json = new JSONObject();
            if (chatId == null)
                throw new InvalidMethodParameterException(this, "chat_id");
            json.put("chat_id", chatId);
            disableNotification.ifPresent(b -> json.put("disable_notification", b));
            replyToMessageId.ifPresent(i -> json.put("reply_to_message_id", i));
            replyMarkup.ifPresent(m -> json.put("reply_markup", m.toJSON()));
            return json;
        }

        @Override
        public MultipartEntityBuilder toMultipart(TelegramBot bot) {
            MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
            multipart.addTextBody("chat_id", chatId.toString());
            disableNotification.ifPresent(b -> multipart.addTextBody("disable_notification", b.toString()));
            replyToMessageId.ifPresent(i -> multipart.addTextBody("reply_to_message_id", i.toString()));
            replyMarkup.ifPresent(m -> multipart.addTextBody("reply_markup", m.toJSON().toString(), ContentType.APPLICATION_JSON));
            return multipart;
        }

        @Override
        public Message execute(TelegramBot bot) {
            JSONObject message = (JSONObject) bot.getExecutor().exec(this);
            return Message.from(bot, message);
        }

    }

}
