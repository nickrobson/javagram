package xyz.nickr.javagram.ext.commands;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.AllArgsConstructor;
import xyz.nickr.javagram.model.chat.ChannelChat;
import xyz.nickr.javagram.model.message.Message;
import xyz.nickr.javagram.model.message.media.ParsedText;
import xyz.nickr.javagram.model.message.media.TextMessageEntity;
import xyz.nickr.javagram.model.message.media.TextMessageEntityType;

/**
 * @author Nick Robson
 */
public class CommandsExtensionDispatcher {

    public static final Class<?>[] EXPECTED_PARAMETERS;
    public static final Class<?>[] EXPECTED_PARAMETERS2;
    public static final String EXPECTED_PARAMETERS_STRING;
    public static final String EXPECTED_PARAMETERS2_STRING;

    static {
        EXPECTED_PARAMETERS = new Class[4];
        EXPECTED_PARAMETERS[0] = Message.class; // the message object
        EXPECTED_PARAMETERS[1] = boolean.class; // true if original, false if edit
        EXPECTED_PARAMETERS[2] = String.class; // the command
        EXPECTED_PARAMETERS[3] = String[].class; // the arguments

        EXPECTED_PARAMETERS2 = new Class[4];
        EXPECTED_PARAMETERS2[0] = Message.class; // the message object
        EXPECTED_PARAMETERS2[1] = boolean.class; // true if original, false if edit
        EXPECTED_PARAMETERS2[2] = String.class; // the command
        EXPECTED_PARAMETERS2[3] = ParsedText.class; // the arguments

        StringBuilder s = new StringBuilder();
        for (Class<?> cls : EXPECTED_PARAMETERS)
            s.append(s.length() == 0 ? "" : ", ").append(getName(cls));
        EXPECTED_PARAMETERS_STRING = s.toString();

        s = new StringBuilder();
        for (Class<?> cls : EXPECTED_PARAMETERS2)
            s.append(s.length() == 0 ? "" : ", ").append(getName(cls));
        EXPECTED_PARAMETERS2_STRING = s.toString();
    }

    private static String getName(Class<?> cls) {
        if (cls.isArray())
            return getName(cls.getComponentType()) + "[]";
        if (cls.isPrimitive())
            return cls.getName();
        if ("java.lang".equals(cls.getPackage().getName()))
            return cls.getSimpleName();
        return cls.getName();
    }

    private final Map<String, MethodListener> listeners;

    public CommandsExtensionDispatcher() {
        this.listeners = new HashMap<>();
    }

    public void register(CommandListener listener) {
        Objects.requireNonNull(listener, "listener");
        Class<? extends CommandListener> cls = listener.getClass();
        for (Method method : cls.getMethods()) {
            method.setAccessible(true);
            Command cmd = method.getAnnotation(Command.class);
            if (cmd == null)
                continue;
            Class<?>[] params = method.getParameterTypes();
            boolean a = Arrays.equals(params, EXPECTED_PARAMETERS);
            boolean b = Arrays.equals(params, EXPECTED_PARAMETERS2);
            String[] methodParams = new String[params.length];
            for (int i = 0; i < params.length; i++) {
                methodParams[i] = getName(params[i]);
            }
            String methodName = String.format("%s.%s(%s)", cls.getName(), method.getName(), String.join(", ", methodParams));
            if (!a && !b) {
                System.err.printf("Ignoring annotated %s as it has incorrect command listener signature.\n", methodName);
                System.err.printf("Allowed method signatures:\n\t%s\n\t%s\n", EXPECTED_PARAMETERS_STRING, EXPECTED_PARAMETERS2_STRING);
                continue;
            }
            String[] aliases = cmd.value();
            if (aliases == null || aliases.length == 0) {
                System.err.printf("Ignoring annotated %s as it has no command aliases\n", methodName);
                continue;
            }
            MethodListener ml = new MethodListener(listener, method, b, cmd);
            for (String alias : aliases)
                if (alias != null)
                    listeners.put(alias.toLowerCase(), ml);
        }
    }

    public void dispatch(Message message, boolean original, String alias, String[] args) {
        MethodListener listener = listeners.get(alias.toLowerCase());
        if (listener == null)
            return;
        if (listener.parsedText) {
            String text = (alias + " " + String.join(" ", args)).trim();
            TextMessageEntity[] entities = new TextMessageEntity[args.length + 1];
            entities[0] = new TextMessageEntity(TextMessageEntityType.TEXT, 0, alias.length());
            int lastEnd = alias.length();
            for (int i = 0; i < args.length; i++) {
                int start = lastEnd + 1;
                entities[i + 1] = new TextMessageEntity(TextMessageEntityType.TEXT, start, args[0].length());
                lastEnd = start + args[0].length();
            }
            ParsedText parsedText = new ParsedText(text, entities);
            listener.exec(message, original, alias, parsedText);
        } else {
            listener.exec(message, original, alias, args);
        }
    }

    public void dispatch(Message message, boolean original, String alias, ParsedText parsedText) {
        MethodListener listener = listeners.get(alias.toLowerCase());
        if (listener == null)
            return;
        if (!listener.parsedText) {
            String[] args = parsedText.getText().split(" ");
            listener.exec(message, original, alias, args);
        } else {
            listener.exec(message, original, alias, parsedText);
        }
    }

    @AllArgsConstructor
    private static class MethodListener {

        private final Object listener;
        private final Method method;
        private final boolean parsedText;
        private final Command cmd;

        void exec(Message message, boolean original, String command, Object args) {
            try {
                if (message.getChat() instanceof ChannelChat && !cmd.channelPosts())
                    return; // not wanted in this case
                method.invoke(listener, message, original, command, args);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
