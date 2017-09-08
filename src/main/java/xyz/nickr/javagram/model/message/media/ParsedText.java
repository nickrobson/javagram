package xyz.nickr.javagram.model.message.media;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Nick Robson
 */
@ToString
@EqualsAndHashCode
public class ParsedText {

    @Getter private final String text;
    private final LinkedList<Token> tokens = new LinkedList<>();

    public ParsedText(String text, TextMessageEntity[] entities) {
        if (text == null || entities == null)
            throw new NullPointerException();
        this.text = text;
        if (entities.length == 0) {
            this.tokens.add(new Token(text, Optional.empty()));
            return;
        }
        int lastEnd = 0;
        for (TextMessageEntity entity : entities) {
            int start = entity.getOffset();
            int end = start + entity.getLength();
            if (start > lastEnd) {
                String spareText = text.substring(lastEnd, start);
                tokens.add(new Token(spareText, Optional.empty()));
            }
            String tokenText = text.substring(start, end);
            tokens.add(new Token(tokenText, Optional.of(entity)));
            lastEnd = end;
        }
        if (lastEnd != text.length()) {
            tokens.add(new Token(text.substring(lastEnd), Optional.empty()));
        }
    }

    public List<Token> getTokens() {
        return Collections.unmodifiableList(tokens);
    }

    @Data
    public static class Token {

        private final String content;
        private final Optional<TextMessageEntity> entity;

    }

}
