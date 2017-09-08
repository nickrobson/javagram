package xyz.nickr.javagram.method;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.InputFile;
import xyz.nickr.javagram.model.chat.Chat;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodSetChatPhoto extends Method<Boolean> {

    private Object chatId;
    private InputFile photo;

    public MethodSetChatPhoto() {
        super("setChatPhoto");
    }

    public MethodSetChatPhoto setChat(Chat chat) {
        this.chatId = this.getChatId(chat);
        return this;
    }

    public MethodSetChatPhoto setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatPhoto setChatId(String chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSetChatPhoto setPhoto(InputFile photo) {
        this.photo = photo;
        return this;
    }

    @Override
    public boolean isJSON() {
        return false;
    }

    @Override
    public MultipartEntityBuilder toMultipart(TelegramBot bot) {
        MultipartEntityBuilder multipart = MultipartEntityBuilder.create();
        if (chatId == null)
            throw new InvalidMethodParameterException(this, "chat_id");
        if (photo == null)
            throw new InvalidMethodParameterException(this, "photo");
        multipart.addTextBody("chat_id", chatId.toString());
        multipart.addBinaryBody("photo", photo.getInputStream(), ContentType.APPLICATION_OCTET_STREAM, photo.getName());
        return multipart;
    }

    @Override
    public Boolean execute(TelegramBot bot) {
        return (boolean) bot.getExecutor().exec(this);
    }

}
