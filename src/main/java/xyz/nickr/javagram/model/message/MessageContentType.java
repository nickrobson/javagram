package xyz.nickr.javagram.model.message;

/**
 * @author Nick Robson
 */
public enum MessageContentType {

    /* media messages */
    AUDIO,
    CONTACT,
    DOCUMENT,
    GAME,
    LOCATION,
    PHOTO,
    STICKER,
    TEXT,
    VENUE,
    VIDEO,
    VIDEO_NOTE,
    VOICE,
    INVOICE,
    SUCCESSFUL_PAYMENT,

    /* service messages */
    NEW_CHAT_MEMBERS,
    NEW_CHAT_MEMBER,
    LEFT_CHAT_MEMBER,
    NEW_CHAT_TITLE,
    NEW_CHAT_PHOTO,
    DELETE_CHAT_PHOTO,
    GROUP_CHAT_CREATED,
    SUPERGROUP_CHAT_CREATED,
    CHANNEL_CHAT_CREATED,
    MIGRATE_TO_CHAT_ID,
    MIGRATE_FROM_CHAT_ID,
    PINNED_MESSAGE;

    public boolean isMediaMessage() {
        return ordinal() < NEW_CHAT_MEMBERS.ordinal();
    }

    public boolean isServiceMessage() {
        return ordinal() >= NEW_CHAT_MEMBERS.ordinal();
    }

}
