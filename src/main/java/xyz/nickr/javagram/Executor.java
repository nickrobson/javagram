package xyz.nickr.javagram;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javax.net.ssl.HttpsURLConnection;
import lombok.Getter;
import org.apache.http.HttpEntity;
import org.json.JSONObject;
import xyz.nickr.javagram.exception.ExecutionFailureException;
import xyz.nickr.javagram.method.Method;

/**
 * @author Nick Robson
 */
@Getter
public class Executor {

    private TelegramBot bot;
    private String token;
    private String baseUrl;

    final void init(TelegramBot bot, String token) {
        this.bot = bot;
        this.token = token;
        this.baseUrl = String.format("https://api.telegram.org/bot%s/", token);
    }

    public final TelegramBot getBot() {
        return this.bot;
    }

    public Object exec(Method method) {
        JSONObject response;
        try {
            URL url = new URL(baseUrl + method.getRoute());
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            if (method.isJSON()) {
                conn.addRequestProperty("Content-Type", "application/json");
                JSONObject body = method.toJSON(bot);
                if (body != null) {
                    byte[] bytes = body.toString().getBytes(StandardCharsets.UTF_8);
                    conn.setDoOutput(true);
                    conn.getOutputStream().write(bytes);
                }
            } else {
                conn.addRequestProperty("Content-Type", "multipart/form-data");
                HttpEntity body = method.toMultipart(bot).build();
                if (body != null) {
                    body.writeTo(conn.getOutputStream());
                }
            }
            StringBuilder json = new StringBuilder();
            InputStream stream = conn.getInputStream();
            byte[] buf = new byte[4096];
            int read;
            while ((read = stream.read(buf)) > 0) {
                json.append(new String(buf, 0, read, StandardCharsets.UTF_8));
            }
            response = new JSONObject(json.toString());
        } catch (IOException ex) {
            throw new ExecutionFailureException("Failed to execute method (" + method.getClass().getName() + ")", ex);
        }
        boolean ok = response.getBoolean("ok");
        if (!ok) {
            String description = response.getString("description");
            int errorCode = response.getInt("error_code");
            JSONObject parameters = response.optJSONObject("parameters");
            throw new ExecutionFailureException(description, errorCode, parameters);
        }
        return response.get("result");
    }

    public void close() {

    }

}
