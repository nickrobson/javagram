package xyz.nickr.javagram.model.message;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidMessageContentException;
import xyz.nickr.javagram.model.Model;
import xyz.nickr.javagram.model.message.media.AudioMessageContent;
import xyz.nickr.javagram.model.message.media.ContactMessageContent;
import xyz.nickr.javagram.model.message.media.DocumentMessageContent;
import xyz.nickr.javagram.model.message.media.GameMessageContent;
import xyz.nickr.javagram.model.message.media.InvoiceMessageContent;
import xyz.nickr.javagram.model.message.media.LocationMessageContent;
import xyz.nickr.javagram.model.message.media.PhotoMessageContent;
import xyz.nickr.javagram.model.message.media.StickerMessageContent;
import xyz.nickr.javagram.model.message.media.SuccessfulPaymentMessageContent;
import xyz.nickr.javagram.model.message.media.TextMessageContent;
import xyz.nickr.javagram.model.message.media.VenueMessageContent;
import xyz.nickr.javagram.model.message.media.VideoMessageContent;
import xyz.nickr.javagram.model.message.media.VideoNoteMessageContent;
import xyz.nickr.javagram.model.message.media.VoiceMessageContent;
import xyz.nickr.javagram.model.message.service.ChannelChatCreatedContent;
import xyz.nickr.javagram.model.message.service.DeletedChatPhotoContent;
import xyz.nickr.javagram.model.message.service.GroupChatCreatedContent;
import xyz.nickr.javagram.model.message.service.LeftChatMemberContent;
import xyz.nickr.javagram.model.message.service.MigrateFromChatIdContent;
import xyz.nickr.javagram.model.message.service.MigrateToChatIdContent;
import xyz.nickr.javagram.model.message.service.NewChatMemberContent;
import xyz.nickr.javagram.model.message.service.NewChatMembersContent;
import xyz.nickr.javagram.model.message.service.NewChatPhotoContent;
import xyz.nickr.javagram.model.message.service.NewChatTitleContent;
import xyz.nickr.javagram.model.message.service.PinnedMessageContent;
import xyz.nickr.javagram.model.message.service.SuperGroupChatCreatedContent;

/**
 * @author Nick Robson
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class MessageContent extends Model {

    private final MessageContentType type;

    public MessageContent(TelegramBot bot, MessageContentType type) {
        super(bot);
        this.type = type;
    }

    public static MessageContent from(TelegramBot bot, JSONObject message) {
        /* media messages */
        if (message.has("text")) {
            return new TextMessageContent(bot, message);
        } else if (message.has("audio")) {
            return new AudioMessageContent(bot, message.getJSONObject("audio"));
        } else if (message.has("document")) {
            return new DocumentMessageContent(bot, message.getJSONObject("document"), message.optString("caption"));
        } else if (message.has("game")) {
            return new GameMessageContent(bot, message.getJSONObject("game"));
        } else if (message.has("photo")) {
            return new PhotoMessageContent(bot, message.getJSONArray("photo"), message.optString("caption"));
        } else if (message.has("sticker")) {
            return new StickerMessageContent(bot, message.getJSONObject("sticker"));
        } else if (message.has("video")) {
            return new VideoMessageContent(bot, message.getJSONObject("video"), message.optString("caption"));
        } else if (message.has("voice")) {
            return new VoiceMessageContent(bot, message.getJSONObject("voice"));
        } else if (message.has("video_note")) {
            return new VideoNoteMessageContent(bot, message.getJSONObject("video_note"));
        } else if (message.has("contact")) {
            return new ContactMessageContent(bot, message.getJSONObject("contact"));
        } else if (message.has("location")) {
            return new LocationMessageContent(bot, message.getJSONObject("location"));
        } else if (message.has("venue")) {
            return new VenueMessageContent(bot, message.getJSONObject("venue"));
        } else if (message.has("invoice")) {
            return new InvoiceMessageContent(bot, message.getJSONObject("invoice"));
        } else if (message.has("successful_payment")) {
            return new SuccessfulPaymentMessageContent(bot, message.getJSONObject("successful_payment"));
        }
        /* service messages */
        if (message.has("new_chat_members")) {
            return new NewChatMembersContent(bot, message.getJSONArray("new_chat_members"));
        } else if (message.has("new_chat_member")) {
            return new NewChatMemberContent(bot, message.getJSONObject("new_chat_member"));
        } else if (message.has("left_chat_member")) {
            return new LeftChatMemberContent(bot, message.getJSONObject("left_chat_member"));
        } else if (message.has("new_chat_title")) {
            return new NewChatTitleContent(bot, message.getString("new_chat_title"));
        } else if (message.has("new_chat_photo")) {
            return new NewChatPhotoContent(bot, message.getJSONArray("new_chat_photo"));
        } else if (message.has("delete_chat_photo")) {
            return new DeletedChatPhotoContent(bot);
        } else if (message.has("group_chat_created")) {
            return new GroupChatCreatedContent(bot);
        } else if (message.has("supergroup_chat_created")) {
            return new SuperGroupChatCreatedContent(bot);
        } else if (message.has("channel_chat_created")) {
            return new ChannelChatCreatedContent(bot);
        } else if (message.has("migrate_to_chat_id")) {
            return new MigrateToChatIdContent(bot, message.getLong("migrate_to_chat_id"));
        } else if (message.has("migrate_from_chat_id")) {
            return new MigrateFromChatIdContent(bot, message.getLong("migrate_from_chat_id"));
        } else if (message.has("pinned_message")) {
            return new PinnedMessageContent(bot, message.getJSONObject("pinned_message"));
        }
        throw new InvalidMessageContentException("No message media class found for " + message);
    }

}
