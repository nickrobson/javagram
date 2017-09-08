package xyz.nickr.javagram.model.message.media;

import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;
import xyz.nickr.javagram.model.payment.OrderInfo;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SuccessfulPaymentMessageContent extends MessageContent {

    private final String currency;
    private final int totalAmount;
    private final String invoicePayload;
    private final Optional<String> shippingOptionId;
    private final Optional<OrderInfo> orderInfo;
    private final String telegramPaymentChargeId;
    private final String providerPaymentChargeId;

    public SuccessfulPaymentMessageContent(TelegramBot bot, JSONObject invoice) {
        super(bot, MessageContentType.SUCCESSFUL_PAYMENT);
        this.currency = invoice.getString("currency");
        this.totalAmount = invoice.getInt("total_amount");
        this.invoicePayload = invoice.getString("invoice_payload");
        this.shippingOptionId = Optional.ofNullable(invoice.optString("shipping_option_id"));
        this.orderInfo = Optional.ofNullable(OrderInfo.from(bot, invoice.optJSONObject("order_info")));
        this.telegramPaymentChargeId = invoice.getString("telegram_payment_charge_id");
        this.providerPaymentChargeId = invoice.getString("provider_payment_charge_id");
    }

}
