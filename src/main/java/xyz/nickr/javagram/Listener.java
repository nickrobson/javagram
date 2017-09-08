package xyz.nickr.javagram;

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
public interface Listener {

    /* On update-received events */
    default void onUpdate(Update update) {}
    default void onMessageUpdate(MessageUpdate update) {}
    default void onMessageEditedUpdate(MessageEditedUpdate update) {}
    default void onChannelPostUpdate(ChannelPostUpdate update) {}
    default void onChannelPostEditedUpdate(ChannelPostEditedUpdate update) {}
    default void onInlineQueryUpdate(InlineQueryUpdate update) {}
    default void onInlineResultUpdate(InlineResultUpdate update) {}
    default void onCallbackQueryUpdate(CallbackQueryUpdate update) {}
    default void onPreCheckoutUpdate(PreCheckoutQueryUpdate update) {}
    default void onShippingQueryUpdate(ShippingQueryUpdate update) {}

}
