package xyz.nickr.javagram.model.inline.result;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;
import xyz.nickr.javagram.model.inline.InlineQueryResult;
import xyz.nickr.javagram.model.inline.content.InputMessageContent;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class InlineQueryResultGame extends InlineQueryResult<InlineQueryResultGame> {

    private String game;

    public InlineQueryResultGame(TelegramBot bot) {
        super(bot, "game");
        this.messageContent = null;
    }

    public InlineQueryResultGame setGameShortName(String game) {
        this.game = game;
        return this;
    }

    public InlineQueryResultGame setMessageContent(InputMessageContent messageContent) {
        throw new UnsupportedOperationException("InlineQueryResultGame does not support having an InputMessageContent");
    }

    @Override
    public JSONObject toJSON() {
        if (game == null)
            throw new InvalidModelParameterException(this, "game");
        JSONObject json = super.toJSON();
        json.put("game_short_name", game);
        return json;
    }
}
