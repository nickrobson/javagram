package xyz.nickr.javagram.model.message.media;

/**
 * @author Nick Robson
 */
public enum TextMessageEntityType {

    /**
     * Only used in {@link ParsedText}
     */
    TEXT,
    MENTION,
    HASHTAG,
    BOT_COMMAND,
    URL,
    EMAIL,
    BOLD,
    ITALIC,
    CODE,
    PRE,
    TEXT_LINK,
    TEXT_MENTION

}
