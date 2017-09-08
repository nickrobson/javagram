package xyz.nickr.javagram.ext.commands;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import javax.swing.text.Document;
import lombok.AllArgsConstructor;
import xyz.nickr.javagram.Listener;
import xyz.nickr.javagram.model.chat.ChatType;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.media.DocumentMessageContent;
import xyz.nickr.javagram.model.message.media.ParsedText;
import xyz.nickr.javagram.model.message.media.PhotoMessageContent;
import xyz.nickr.javagram.model.message.media.TextMessageContent;
import xyz.nickr.javagram.model.message.media.VideoMessageContent;
import xyz.nickr.javagram.model.update.ChannelPostEditedUpdate;
import xyz.nickr.javagram.model.update.ChannelPostUpdate;
import xyz.nickr.javagram.model.update.MessageEditedUpdate;
import xyz.nickr.javagram.model.update.MessageUpdate;

/**
 * @author Nick Robson
 */
@AllArgsConstructor
public class CommandsExtensionListener implements Listener {

    private final CommandsExtension ext;

    @Override
    public void onMessageUpdate(MessageUpdate update) {
        onMessage(update.getMessage(), true);
    }

    @Override
    public void onMessageEditedUpdate(MessageEditedUpdate update) {
        onMessage(update.getMessage(), false);
    }

    @Override
    public void onChannelPostUpdate(ChannelPostUpdate update) {
        onMessage(update.getMessage(), true);
    }

    @Override
    public void onChannelPostEditedUpdate(ChannelPostEditedUpdate update) {
        onMessage(update.getMessage(), false);
    }

    private void onMessage(Message message, boolean original) {
        MessageContent content = message.getContent();
        boolean isPrivate = message.getChat().getType() == ChatType.PRIVATE;
        if (content == null)
            return;
        switch (content.getType()) {
            case TEXT: {
                TextMessageContent textContent = (TextMessageContent) content;
                if (textContent.isCommand()) {
                    String[] command = textContent.getCommand();
                    if (isPrivate || (command[1] != null && ext.bot.getBotUsername().equalsIgnoreCase(command[1]))) {
                        String alias = command[0];
                        ParsedText parsedText = textContent.parse();
                        ext.dispatcher.dispatch(message, original, alias, parsedText);
                    }
                }
                break;
            }
            case PHOTO: {
                PhotoMessageContent photoContent = (PhotoMessageContent) content;
                Optional<String> caption = photoContent.getCaption();
                caption.ifPresent(s -> exec(message, original, isPrivate, s));
                break;
            }
            case DOCUMENT: {
                DocumentMessageContent documentContent = (DocumentMessageContent) content;
                Optional<String> caption = documentContent.getCaption();
                caption.ifPresent(s -> exec(message, original, isPrivate, s));
                break;
            }
            case VIDEO: {
                VideoMessageContent videoContent = (VideoMessageContent) content;
                Optional<String> caption = videoContent.getCaption();
                caption.ifPresent(s -> exec(message, original, isPrivate, s));
                break;
            }
        }
    }

    private void exec(Message m, boolean original, boolean isPrivate, String cmdString) {
        if (!cmdString.startsWith("/"))
            return;
        String[] words = cmdString.substring(1).split(" ");
        String[] cmd = words[0].toLowerCase().split("@", 1);
        if (cmd.length < 2)
            cmd = new String[]{ cmd[0], null };
        if (isPrivate || (cmd[1] != null && ext.bot.getBotUsername().equalsIgnoreCase(cmd[1]))) {
            words[0] = cmd[0];
            ext.dispatcher.dispatch(m, original, cmd[0], words);
        }
    }

}
