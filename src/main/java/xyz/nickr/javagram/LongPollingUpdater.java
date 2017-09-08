package xyz.nickr.javagram;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.method.MethodGetUpdates;
import xyz.nickr.javagram.model.update.Update;

/**
 * @author Nick Robson
 */
public class LongPollingUpdater extends Updater {

    private volatile Thread thread;
    private volatile PollingThread pollingThread;

    @Getter @Setter private boolean previousUpdates;
    @Getter @Setter private int limit = 100, timeout = 10;

    public LongPollingUpdater() {
        this(false);
    }
    public LongPollingUpdater(boolean previousUpdates) {
        this.previousUpdates = previousUpdates;
    }

    @Override
    public void start() {
        if (thread != null)
            return;
        pollingThread = new PollingThread();
        thread = new Thread(pollingThread);
        thread.start();
    }

    @Override
    public void stop() {
        pollingThread.running = false;
    }

    private class PollingThread implements Runnable {

        private volatile boolean running = true;
        private volatile Long lastUpdateId;

        public void run() {
            TelegramBot bot = getBot();
            MethodGetUpdates getUpdates = new MethodGetUpdates();

            getUpdates.setLimit(limit);
            getUpdates.setTimeout(timeout);
            if (!LongPollingUpdater.this.previousUpdates) {
                getUpdates.setOffset(-1);
            }

            while (this.running) {
                if (this.lastUpdateId != null)
                    getUpdates.setOffset(this.lastUpdateId + 1);

                try {
                    Update[] updates = getUpdates.execute(bot);
                    for (Update update : updates) {
                        bot.getDispatcher().dispatch(update);
                        this.lastUpdateId = update.getUpdateId();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    break;
                }
            }
        }
    }

}
