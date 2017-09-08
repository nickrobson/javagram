package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.replymarkup.ReplyMarkup;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodEditMessageCaption extends Method<Message> {

    private Object chatId;
    private long messageId = -1;
    private String inlineMessageId;
    private String caption;
    private Optional<ReplyMarkup> replyMarkup = Optional.empty();

    public MethodEditMessageCaption() {
        super("editMessageCaption");
    }

    public MethodEditMessageCaption setChatMessage(Chat chat, long messageId) {
        this.chatId = this.getChatId(chat);
        this.messageId = messageId;
        this.inlineMessageId = null;
        return this;
    }

    public MethodEditMessageCaption setChatMessage(long chatId, long messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = null;
        return this;
    }

    public MethodEditMessageCaption setChatMessage(String chatId, long messageId) {
        this.chatId = chatId;
        this.messageId = messageId;
        this.inlineMessageId = null;
        return this;
    }

    public MethodEditMessageCaption setInlineMessage(String inlineMessageId) {
        this.chatId = null;
        this.inlineMessageId = inlineMessageId;
        return this;
    }

    public MethodEditMessageCaption setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    public MethodEditMessageCaption setReplyMarkup(ReplyMarkup replyMarkup) {
        this.replyMarkup = Optional.ofNullable(replyMarkup);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId != null) {
            json.put("chat_id", chatId);
            if (messageId < 0)
                throw new InvalidMethodParameterException(this, "message_id", ">= 0");
            json.put("message_id", messageId);
        } else if (inlineMessageId != null) {
            json.put("inline_message_id", inlineMessageId);
        } else {
            throw new InvalidMethodParameterException(this, "(chat_id AND message_id) XOR inline_message_id");
        }
        if (caption == null) {
            throw new InvalidMethodParameterException(this, "caption");
        }
        json.put("caption", caption);
        replyMarkup.ifPresent(m -> json.put("reply_markup", m.toJSON()));
        return json;
    }

    @Override
    public Message execute(TelegramBot bot) {
        Object object = bot.getExecutor().exec(this);
        if (object instanceof Boolean)
            return null;
        JSONObject message = (JSONObject) object;
        return Message.from(bot, message);
    }

}
