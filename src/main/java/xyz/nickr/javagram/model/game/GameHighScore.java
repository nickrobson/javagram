package xyz.nickr.javagram.model.game;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class GameHighScore extends Model {

    private final int position;
    private final User user;
    private final int score;

    public GameHighScore(TelegramBot bot, JSONObject game_high_score) {
        super(bot);
        this.position = game_high_score.getInt("position");
        this.user = User.from(bot, game_high_score.getJSONObject("user"));
        this.score = game_high_score.getInt("score");
    }

    public static GameHighScore from(TelegramBot bot, JSONObject game_high_score) {
        return game_high_score != null
                ? new GameHighScore(bot, game_high_score)
                : null;
    }

}
