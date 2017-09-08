package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.chat.Chat;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.payment.LabeledPrice;
import xyz.nickr.javagram.model.replymarkup.InlineKeyboardMarkup;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class MethodSendInvoice extends Method {

    private long chatId = -1;
    private String title;
    private String description;
    private String payload;
    private String providerToken;
    private String startParameter;
    private String currency;
    private LabeledPrice[] prices;
    private Optional<String> photoUrl = Optional.empty();
    private Optional<Integer> photoSize = Optional.empty();
    private Optional<Integer> photoWidth = Optional.empty();
    private Optional<Integer> photoHeight = Optional.empty();
    private Optional<Boolean> needName = Optional.empty();
    private Optional<Boolean> needPhoneNumber = Optional.empty();
    private Optional<Boolean> needEmail = Optional.empty();
    private Optional<Boolean> needShippingAddress = Optional.empty();
    private Optional<Boolean> isFlexible = Optional.empty();
    private Optional<Boolean> disableNotification = Optional.empty();
    private Optional<Integer> replyToMessageId = Optional.empty();
    private Optional<InlineKeyboardMarkup> replyMarkup = Optional.empty();


    public MethodSendInvoice() {
        super("sendInvoice");
    }

    public MethodSendInvoice setChat(Chat chat) {
        this.chatId = chat.getId();
        return this;
    }

    public MethodSendInvoice setChatId(long chatId) {
        this.chatId = chatId;
        return this;
    }

    public MethodSendInvoice setTitle(String title) {
        this.title = title;
        return this;
    }

    public MethodSendInvoice setDescription(String description) {
        this.description = description;
        return this;
    }

    public MethodSendInvoice setPayload(String payload) {
        this.payload = payload;
        return this;
    }

    public MethodSendInvoice setProviderToken(String providerToken) {
        this.providerToken = providerToken;
        return this;
    }

    public MethodSendInvoice setStartParameter(String startParameter) {
        this.startParameter = startParameter;
        return this;
    }

    public MethodSendInvoice setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public MethodSendInvoice setPrices(LabeledPrice[] prices) {
        this.prices = prices;
        return this;
    }

    public MethodSendInvoice setPhotoUrl(String photoUrl) {
        this.photoUrl = Optional.ofNullable(photoUrl);
        return this;
    }

    public MethodSendInvoice setPhotoSize(Integer photoSize) {
        this.photoSize = Optional.ofNullable(photoSize);
        return this;
    }

    public MethodSendInvoice setPhotoWidth(Integer photoWidth) {
        this.photoWidth = Optional.ofNullable(photoWidth);
        return this;
    }

    public MethodSendInvoice setPhotoHeight(Integer photoHeight) {
        this.photoHeight = Optional.ofNullable(photoHeight);
        return this;
    }

    public MethodSendInvoice setNeedName(Boolean needName) {
        this.needName = Optional.ofNullable(needName);
        return this;
    }

    public MethodSendInvoice setNeedPhoneNumber(Boolean needPhoneNumber) {
        this.needPhoneNumber = Optional.ofNullable(needPhoneNumber);
        return this;
    }

    public MethodSendInvoice setNeedEmail(Boolean needEmail) {
        this.needEmail = Optional.ofNullable(needEmail);
        return this;
    }

    public MethodSendInvoice setNeedShippingAddress(Boolean needShippingAddress) {
        this.needShippingAddress = Optional.ofNullable(needShippingAddress);
        return this;
    }

    public MethodSendInvoice setFlexible(Boolean isFlexible) {
        this.isFlexible = Optional.ofNullable(isFlexible);
        return this;
    }

    public MethodSendInvoice setDisableNotification(Boolean disableNotification) {
        this.disableNotification = Optional.ofNullable(disableNotification);
        return this;
    }

    public MethodSendInvoice setReplyToMessageId(Integer replyToMessageId) {
        this.replyToMessageId = Optional.ofNullable(replyToMessageId);
        return this;
    }

    public MethodSendInvoice setReplyMarkup(InlineKeyboardMarkup replyMarkup) {
        this.replyMarkup = Optional.ofNullable(replyMarkup);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (chatId < 0)
            throw new InvalidMethodParameterException(this, "chat_id", ">= 0");
        if (title == null)
            throw new InvalidMethodParameterException(this, "title");
        if (description == null)
            throw new InvalidMethodParameterException(this, "description");
        if (payload == null)
            throw new InvalidMethodParameterException(this, "payload");
        if (providerToken == null)
            throw new InvalidMethodParameterException(this, "provider_token");
        if (startParameter == null)
            throw new InvalidMethodParameterException(this, "start_parameter");
        if (currency == null)
            throw new InvalidMethodParameterException(this, "currency");
        if (prices == null)
            throw new InvalidMethodParameterException(this, "prices");
        json.put("chat_id", chatId);
        json.put("title", title);
        json.put("description", description);
        json.put("payload", payload);
        json.put("provider_token", providerToken);
        json.put("start_parameter", startParameter);
        json.put("currency", currency);
        JSONArray arr = new JSONArray();
        for (LabeledPrice price : prices)
            arr.put(price.toJSON());
        json.put("prices", arr);

        photoUrl.ifPresent(s -> json.put("photo_url", s));
        photoSize.ifPresent(i -> json.put("photo_size", i));
        photoWidth.ifPresent(i -> json.put("photo_width", i));
        photoHeight.ifPresent(i -> json.put("photo_height", i));

        needName.ifPresent(b -> json.put("need_name", b));
        needPhoneNumber.ifPresent(b -> json.put("need_phone_number", b));
        needEmail.ifPresent(b -> json.put("need_email", b));
        needShippingAddress.ifPresent(b -> json.put("need_shipping_address", b));

        isFlexible.ifPresent(b -> json.put("is_flexible", b));
        disableNotification.ifPresent(b -> json.put("disable_notification", b));
        replyToMessageId.ifPresent(i -> json.put("reply_to_message_id", i));
        replyMarkup.ifPresent(m -> json.put("reply_markup", m.toJSON()));

        return json;
    }

    @Override
    public Message execute(TelegramBot bot) {
        JSONObject message = (JSONObject) bot.getExecutor().exec(this);
        return Message.from(bot, message);
    }

}
