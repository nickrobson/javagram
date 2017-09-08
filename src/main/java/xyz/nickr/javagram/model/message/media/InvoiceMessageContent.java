package xyz.nickr.javagram.model.message.media;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.MessageContentType;

/**
 * @author Nick Robson
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InvoiceMessageContent extends MessageContent {

    private final String title;
    private final String description;
    private final String startParameter;
    private final String currency;
    private final int totalAmount;

    public InvoiceMessageContent(TelegramBot bot, JSONObject invoice) {
        super(bot, MessageContentType.INVOICE);
        this.title = invoice.getString("title");
        this.description = invoice.getString("description");
        this.startParameter = invoice.getString("start_parameter");
        this.currency = invoice.getString("currency");
        this.totalAmount = invoice.getInt("total_amount");
    }

}
