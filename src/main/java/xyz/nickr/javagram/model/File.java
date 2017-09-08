package xyz.nickr.javagram.model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.method.MethodGetFile;

/**
 * @author Nick Robson
 */
@Data
@ToString(doNotUseGetters = true)
@EqualsAndHashCode(callSuper = false, of = "fileId")
public class File extends Model {

    public static final String FILE_URL_FORMAT = "https://api.telegram.org/file/bot%s/%s";

    private final String fileId;
    @Setter(AccessLevel.NONE) private volatile Optional<Long> fileSize;
    @Setter(AccessLevel.NONE) private volatile Optional<String> filePath;

    @Getter(AccessLevel.NONE) @Setter(AccessLevel.NONE)
    private volatile boolean loaded;

    public File(TelegramBot bot, String fileId) {
        super(bot);
        this.fileId = fileId;
        this.fileSize = Optional.empty();
        this.filePath = Optional.empty();
        this.loaded = false;
    }

    public File(TelegramBot bot, JSONObject file) {
        super(bot);
        this.fileId = file.getString("file_id");
        this.fileSize = Optional.ofNullable(file.has("file_size") ? file.getLong("file_size") : null);
        this.filePath = Optional.ofNullable(file.optString("file_path"));
        this.loaded = true;
    }

    public Optional<Long> getFileSize() {
        load();
        return this.fileSize;
    }

    public Optional<String> getFilePath() {
        load();
        return this.filePath;
    }

    public boolean download(java.io.File destination) {
        load();
        if (!this.filePath.isPresent())
            return false;
        String url = String.format(FILE_URL_FORMAT, getBot().getExecutor().getToken(), this.filePath.get());
        try {
            URL u = new URL(url);
            try (InputStream stream = u.openStream()) {
                Files.copy(stream, destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
            return true;
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private void load() {
        if (loaded & (loaded = true))
            return;
        MethodGetFile method = new MethodGetFile();
        method.setFileId(this.fileId);

        JSONObject file = (JSONObject) getBot().getExecutor().exec(method);
        this.fileSize = Optional.ofNullable(file.has("file_size") ? file.getLong("file_size") : null);
        this.filePath = Optional.ofNullable(file.optString("file_path"));
    }

}
