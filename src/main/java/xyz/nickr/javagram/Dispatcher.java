package xyz.nickr.javagram;

import java.util.LinkedHashSet;
import xyz.nickr.javagram.model.update.Update;
import xyz.nickr.javagram.model.update.CallbackQueryUpdate;
import xyz.nickr.javagram.model.update.ChannelPostEditedUpdate;
import xyz.nickr.javagram.model.update.ChannelPostUpdate;
import xyz.nickr.javagram.model.update.InlineQueryUpdate;
import xyz.nickr.javagram.model.update.InlineResultUpdate;
import xyz.nickr.javagram.model.update.MessageEditedUpdate;
import xyz.nickr.javagram.model.update.MessageUpdate;
import xyz.nickr.javagram.model.update.PreCheckoutQueryUpdate;
import xyz.nickr.javagram.model.update.ShippingQueryUpdate;

/**
 * @author Nick Robson
 */
public class Dispatcher {

    private final LinkedHashSet<Listener> listeners;

    public Dispatcher() {
        this.listeners = new LinkedHashSet<>();
    }

    public void register(Listener listener) {
        this.listeners.add(listener);
    }

    public void dispatch(Update update) {
        for (Listener listener : listeners) {
            listener.onUpdate(update);
            switch (update.getUpdateType()) {
                case MESSAGE:
                    listener.onMessageUpdate((MessageUpdate) update);
                    break;
                case MESSAGE_EDITED:
                    listener.onMessageEditedUpdate((MessageEditedUpdate) update);
                    break;
                case CHANNEL_POST:
                    listener.onChannelPostUpdate((ChannelPostUpdate) update);
                    break;
                case CHANNEL_POST_EDITED:
                    listener.onChannelPostEditedUpdate((ChannelPostEditedUpdate) update);
                    break;
                case INLINE_QUERY:
                    listener.onInlineQueryUpdate((InlineQueryUpdate) update);
                    break;
                case INLINE_RESULT:
                    listener.onInlineResultUpdate((InlineResultUpdate) update);
                    break;
                case CALLBACK_QUERY:
                    listener.onCallbackQueryUpdate((CallbackQueryUpdate) update);
                    break;
                case SHIPPING:
                    listener.onShippingQueryUpdate((ShippingQueryUpdate) update);
                    break;
                case PRE_CHECKOUT:
                    listener.onPreCheckoutUpdate((PreCheckoutQueryUpdate) update);
                    break;
            }
        }
    }

}
