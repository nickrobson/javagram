package xyz.nickr.javagram.method;

import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMethodParameterException;
import xyz.nickr.javagram.model.UserProfilePhotos;

/**
 * @author Nick Robson
 */
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public class MethodGetUserProfilePhotos extends Method<UserProfilePhotos> {

    private long userId = -1;
    private Optional<Integer> offset = Optional.empty();
    private Optional<Integer> limit = Optional.empty();

    public MethodGetUserProfilePhotos() {
        super("getUserProfilePhotos");
    }

    public MethodGetUserProfilePhotos setUserId(long userId) {
        this.userId = userId;
        return this;
    }

    public MethodGetUserProfilePhotos setOffset(Integer offset) {
        this.offset = Optional.ofNullable(offset);
        return this;
    }

    public MethodGetUserProfilePhotos setLimit(Integer limit) {
        this.limit = Optional.ofNullable(limit);
        return this;
    }

    @Override
    public JSONObject toJSON(TelegramBot bot) {
        JSONObject json = new JSONObject();
        if (userId < 0)
            throw new InvalidMethodParameterException(this, "user_id", ">= 0");
        json.put("user_id", userId);
        offset.ifPresent(i -> json.put("offset", i));
        limit.ifPresent(i -> json.put("limit", i));
        return json;
    }

    @Override
    public UserProfilePhotos execute(TelegramBot bot) {
        JSONObject user_profile_photos = (JSONObject) bot.getExecutor().exec(this);
        return new UserProfilePhotos(bot, user_profile_photos);
    }

}
