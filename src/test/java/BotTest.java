import java.util.Arrays;
import xyz.nickr.javagram.Listener;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.LongPollingUpdater;
import xyz.nickr.javagram.ext.commands.Command;
import xyz.nickr.javagram.ext.commands.CommandListener;
import xyz.nickr.javagram.ext.commands.CommandsExtension;
import xyz.nickr.javagram.method.MethodSendMessage;
import xyz.nickr.javagram.method.MethodSendVoice;
import xyz.nickr.javagram.model.File;
import xyz.nickr.javagram.model.message.FileMessageContent;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.message.MessageContent;
import xyz.nickr.javagram.model.message.media.TextMessageContent;
import xyz.nickr.javagram.model.message.media.ParsedText;
import xyz.nickr.javagram.model.message.media.VoiceMessageContent;
import xyz.nickr.javagram.model.update.MessageUpdate;
import xyz.nickr.javagram.model.update.Update;
import xyz.nickr.javagram.model.user.User;

/**
 * @author Nick Robson
 */
public class BotTest {

    public static void main(String[] args) {
        String token = args.length > 0 ? args[0] : System.getenv("BOT_TOKEN");

        LongPollingUpdater updater = new LongPollingUpdater(false);
        updater.setTimeout(30);

        TelegramBot bot = new TelegramBot(token, updater);
        CommandsExtension ext = bot.loadExtension(CommandsExtension::new);
        ext.getDispatcher().register(new C());
        bot.getDispatcher().register(new L());
        bot.start();
    }

    private static class L implements Listener {

        public void onUpdate(Update update) {
            System.out.println(update);
        }

        @Override
        public void onMessageUpdate(MessageUpdate update) {
            Message message = update.getMessage();
            MessageContent content = message.getContent();
            if (content instanceof TextMessageContent) {
                ParsedText parsed = ((TextMessageContent) content).parse();
                System.out.println(parsed.getTokens());
            } else if (content instanceof VoiceMessageContent) {
                VoiceMessageContent filectx = (VoiceMessageContent) content;
                File file = filectx.getFile();

                MethodSendVoice msv = new MethodSendVoice();
                msv.setChat(message.getChat());
                msv.setVoice(file.getFileId());
                update.getBot().getExecutor().exec(msv);

            } else if (content instanceof FileMessageContent) {
                FileMessageContent filectx = (FileMessageContent) content;
                File file = filectx.getFile();
            }

            MethodSendMessage msm = new MethodSendMessage();
            msm.setChatId(message.getChat().getId());
            msm.setText("Thanks for the " + content.getType() + " message, " + message.getSender().map(User::getFirstName).orElse("[no name]") + "!");
            msm.setReplyToMessageId(message.getMessageId());
            update.getBot().getExecutor().exec(msm);
        }
    }

    private static class C implements CommandListener {

        @Command("test")
        public void test(Message message, boolean original, String alias, String[] args) {
            System.out.println(message);
            System.out.println(original);
            System.out.println(alias);
            System.out.println(Arrays.toString(args));
        }

        @Command("test2")
        public void test2(Message message, boolean original, String alias, ParsedText args) {
            System.out.println(message);
            System.out.println(original);
            System.out.println(alias);
            System.out.println(args);
        }

    }

}
