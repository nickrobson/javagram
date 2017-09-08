package xyz.nickr.javagram.model.replymarkup;

import java.util.Arrays;
import java.util.Optional;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import xyz.nickr.javagram.TelegramBot;
import xyz.nickr.javagram.exception.InvalidModelParameterException;

/**
 * @author Nick Robson
 */
@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ReplyKeyboardMarkup extends ReplyMarkup {

    private KeyboardButton[][] buttons;
    private Optional<Boolean> resizeKeyboard = Optional.empty();
    private Optional<Boolean> oneTimeKeyboard = Optional.empty();
    private Optional<Boolean> selective = Optional.empty();

    public ReplyKeyboardMarkup(TelegramBot bot) {
        super(bot);
    }

    public KeyboardButton[][] getButtons() {
        return buttons;
    }

    public KeyboardButton[] getRow(int rowIndex) {
        return (rowIndex >= 0 && rowIndex < buttons.length)
                ? buttons[rowIndex]
                : null;
    }

    public KeyboardButton getButton(int rowIndex, int colIndex) {
        KeyboardButton[] row = getRow(rowIndex);
        return row != null && (colIndex >= 0 && colIndex < row.length)
                ? row[colIndex]
                : null;
    }

    public ReplyKeyboardMarkup setRow(int rowIndex, KeyboardButton[] newRow) {
        if (rowIndex == buttons.length)
            return addRow(newRow);
        KeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        this.buttons[rowIndex] = newRow;
        return this;
    }

    public ReplyKeyboardMarkup setButton(int rowIndex, int colIndex, KeyboardButton newButton) {
        if (rowIndex == buttons.length && colIndex == 0)
            return addRow(new KeyboardButton[]{ newButton });
        KeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        if (colIndex == row.length)
            return addButton(rowIndex, newButton);
        KeyboardButton button = getButton(rowIndex, colIndex);
        if (button == null)
            throw new IllegalArgumentException("no button at " + rowIndex + "," + colIndex);
        row[colIndex] = button;
        return this;
    }

    public ReplyKeyboardMarkup addRow(KeyboardButton[] row) {
        this.buttons = Arrays.copyOf(buttons, buttons.length + 1);
        this.buttons[buttons.length - 1] = row;
        return this;
    }

    public ReplyKeyboardMarkup addRow() {
        return addRow(new KeyboardButton[0]);
    }

    public ReplyKeyboardMarkup addButton(int rowIndex, KeyboardButton button) {
        KeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        row = Arrays.copyOf(row, row.length + 1);
        row[row.length - 1] = button;
        this.buttons[rowIndex] = row;
        return this;
    }

    public ReplyKeyboardMarkup setResizeKeyboard(Boolean resizeKeyboard) {
        this.resizeKeyboard = Optional.ofNullable(resizeKeyboard);
        return this;
    }

    public ReplyKeyboardMarkup setOneTimeKeyboard(Boolean oneTimeKeyboard) {
        this.oneTimeKeyboard = Optional.ofNullable(oneTimeKeyboard);
        return this;
    }

    public ReplyKeyboardMarkup setSelective(Boolean selective) {
        this.selective = Optional.ofNullable(selective);
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        if (buttons == null)
            throw new InvalidModelParameterException(this, "keyboard");
        JSONArray rowsArray = new JSONArray();
        for (KeyboardButton[] row : buttons) {
            JSONArray rowArray = new JSONArray();
            for (KeyboardButton button : row) {
                rowArray.put(button.toJSON());
            }
            rowsArray.put(rowArray);
        }
        json.put("keyboard", rowsArray);
        resizeKeyboard.ifPresent(b -> json.put("resize_keyboard", b));
        oneTimeKeyboard.ifPresent(b -> json.put("one_time_keyboard", b));
        selective.ifPresent(b -> json.put("selective", b));
        return json;
    }

}
