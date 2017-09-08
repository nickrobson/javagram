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
public class InlineKeyboardMarkup extends ReplyMarkup {

    private InlineKeyboardButton[][] buttons;

    public InlineKeyboardMarkup(TelegramBot bot) {
        super(bot);
    }

    public InlineKeyboardButton[][] getButtons() {
        return buttons;
    }

    public InlineKeyboardButton[] getRow(int rowIndex) {
        return (rowIndex >= 0 && rowIndex < buttons.length)
                ? buttons[rowIndex]
                : null;
    }

    public InlineKeyboardButton getButton(int rowIndex, int colIndex) {
        InlineKeyboardButton[] row = getRow(rowIndex);
        return row != null && (colIndex >= 0 && colIndex < row.length)
                ? row[colIndex]
                : null;
    }

    public InlineKeyboardMarkup setRow(int rowIndex, InlineKeyboardButton[] newRow) {
        if (rowIndex == buttons.length)
            return addRow(newRow);
        InlineKeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        this.buttons[rowIndex] = newRow;
        return this;
    }

    public InlineKeyboardMarkup setButton(int rowIndex, int colIndex, InlineKeyboardButton newButton) {
        if (rowIndex == buttons.length && colIndex == 0)
            return addRow(new InlineKeyboardButton[]{ newButton });
        InlineKeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        if (colIndex == row.length)
            return addButton(rowIndex, newButton);
        InlineKeyboardButton button = getButton(rowIndex, colIndex);
        if (button == null)
            throw new IllegalArgumentException("no button at " + rowIndex + "," + colIndex);
        row[colIndex] = button;
        return this;
    }

    public InlineKeyboardMarkup clearButtons() {
        this.buttons = new InlineKeyboardButton[0][];
        return this;
    }

    public InlineKeyboardMarkup addRow(InlineKeyboardButton[] row) {
        this.buttons = Arrays.copyOf(buttons, buttons.length + 1);
        this.buttons[buttons.length - 1] = row;
        return this;
    }

    public InlineKeyboardMarkup addRow() {
        return addRow(new InlineKeyboardButton[0]);
    }

    public InlineKeyboardMarkup addButton(int rowIndex, InlineKeyboardButton button) {
        InlineKeyboardButton[] row = getRow(rowIndex);
        if (row == null)
            throw new IllegalArgumentException("no row at index " + rowIndex);
        row = Arrays.copyOf(row, row.length + 1);
        row[row.length - 1] = button;
        this.buttons[rowIndex] = row;
        return this;
    }

    @Override
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        if (buttons == null)
            throw new InvalidModelParameterException(this, "inline_keyboard");
        JSONArray rowsArray = new JSONArray();
        for (InlineKeyboardButton[] row : buttons) {
            JSONArray rowArray = new JSONArray();
            for (InlineKeyboardButton button : row) {
                rowArray.put(button.toJSON());
            }
            rowsArray.put(rowArray);
        }
        json.put("inline_keyboard", rowsArray);
        return json;
    }

}
