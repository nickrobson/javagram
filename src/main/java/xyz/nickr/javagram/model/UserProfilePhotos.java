package xyz.nickr.javagram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class UserProfilePhotos extends Model {

    private final int totalPhotos;
    private final PhotoSize[][] photos;

    public UserProfilePhotos(TelegramBot bot, JSONObject user_profile_photos) {
        super(bot);
        this.totalPhotos = user_profile_photos.getInt("total_count");
        JSONArray photos = user_profile_photos.getJSONArray("photos");
        this.photos = new PhotoSize[photos.length()][];
        for (int i = 0; i < photos.length(); i++) {
            JSONArray sizes = photos.getJSONArray(i);
            this.photos[i] = new PhotoSize[sizes.length()];
            for (int j = 0; j < sizes.length(); j++) {
                this.photos[i][j] = PhotoSize.from(bot, sizes.getJSONObject(i));
            }
        }
    }

}
