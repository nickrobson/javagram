package xyz.nickr.javagram.model.replymarkup;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.game.CallbackGame;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class InlineKeyboardButton extends Model {

    private String text;
    private Optional<String> url = Optional.empty();
    private Optional<String> callbackData = Optional.empty();
    private Optional<String> switchInlineQuery = Optional.empty();
    private Optional<String> switchInlineQueryCurrentChat = Optional.empty();
    private Optional<CallbackGame> callbackGame = Optional.empty();
    private Optional<Boolean> pay = Optional.empty();

    public InlineKeyboardButton(TelegramBot bot) {
        super(bot);
    }

    public InlineKeyboardButton setText(String text) {
        this.text = text;
        return this;
    }

    public InlineKeyboardButton setUrl(String url) {
        this.url = Optional.ofNullable(url);
        return this;
    }

    public InlineKeyboardButton setCallbackData(String callbackData) {
        this.callbackData = Optional.ofNullable(callbackData);
        return this;
    }

    public InlineKeyboardButton setSwitchInlineQuery(String switchInlineQuery) {
        this.switchInlineQuery = Optional.ofNullable(switchInlineQuery);
        return this;
    }

    public InlineKeyboardButton setSwitchInlineQueryCurrentChat(String switchInlineQueryCurrentChat) {
        this.switchInlineQueryCurrentChat = Optional.ofNullable(switchInlineQueryCurrentChat);
        return this;
    }

    public InlineKeyboardButton setCallbackGame(CallbackGame callbackGame) {
        this.callbackGame = Optional.ofNullable(callbackGame);
        return this;
    }

    public InlineKeyboardButton setPay(Boolean pay) {
        this.pay = Optional.ofNullable(pay);
        return this;
    }

    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        if (text == null)
            throw new InvalidModelParameterException(this, "text");
        json.put("text", text);
        url.ifPresent(s -> json.put("url", s));
        callbackData.ifPresent(s -> json.put("callback_data", s));
        switchInlineQuery.ifPresent(s -> json.put("switch_inline_query", s));
        switchInlineQueryCurrentChat.ifPresent(s -> json.put("switch_inline_query_current_chat", s));
        callbackGame.ifPresent(g -> json.put("callback_game", g.toJSON()));
        pay.ifPresent(p -> json.put("pay", p));
        return json;
    }

}
