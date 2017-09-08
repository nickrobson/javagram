package xyz.nickr.javagram;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import lombok.Getter;
import xyz.nickr.javagram.ext.Extension;
import xyz.nickr.javagram.method.MethodGetMe;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
public class TelegramBot {

    @Getter private final Updater updater;
    @Getter private final Executor executor;
    @Getter private final Dispatcher dispatcher;

    private final Map<Class<? extends Extension>, Extension> extensions;

    @Getter private long botId;
    @Getter private String botName, botUsername;

    public TelegramBot(String token, Updater updater) {
        this.updater = updater;
        this.executor = new Executor();
        this.dispatcher = new Dispatcher();
        this.extensions = new HashMap<>();

        this.updater.init(this, token);
        this.executor.init(this, token);
    }

    @SuppressWarnings("unchecked")
    public <T extends Extension> T getExtension(Class<T> extensionClass) {
        return (T) extensions.get(extensionClass);
    }

    @SuppressWarnings("unchecked")
    public <T extends Extension> T getExtension(Class<T> extensionClass, Supplier<T> supplier) {
        T t = getExtension(extensionClass);
        if (t != null)
            return t;
        t = supplier.get();
        t.register(this);
        extensions.put(extensionClass, t);
        return t;
    }

    @SuppressWarnings("unchecked")
    public <T extends Extension> T loadExtension(Supplier<T> supplier) {
        T t = supplier.get();
        Class<? extends Extension> cls = t.getClass();
        if (extensions.containsKey(cls))
            return (T) extensions.get(cls);
        t.register(this);
        extensions.put(cls, t);
        return t;
    }

    public void start() {
        MethodGetMe getMe = new MethodGetMe();
        User user = getMe.execute(this);
        this.botId = user.getId();
        this.botName = user.getFirstName();
        this.botUsername = user.getUsername().get();

        System.out.printf("Logged in as @%s\n", botUsername);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            updater.stop();
            executor.close();
        }));
        updater.start();
    }

    public void stop() {
        updater.stop();
        executor.close();
    }

}
