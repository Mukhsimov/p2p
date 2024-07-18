package uz.pdp.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyKeyboardMaker {


    public ReplyKeyboardMarkup replyMakerWithList(List<List<String>> buttons) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        markup.setKeyboard(keyboardRows);
        for (List<String> button : buttons) {
            KeyboardRow row = new KeyboardRow();
            keyboardRows.add(row);
            for (String s : button) {
                row.add(s);
            }
        }
        return markup;
    }

    public ReplyKeyboardMarkup replyMaker(String[][] buttons) {
        ReplyKeyboardMarkup markup = new ReplyKeyboardMarkup();
        markup.setResizeKeyboard(true);
        markup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        markup.setKeyboard(keyboardRows);
        for (String[] button : buttons) {
            KeyboardRow row = new KeyboardRow();
            keyboardRows.add(row);
            for (String s : button) {
                if (s.equals("contact")) {
                    row.add(KeyboardButton.builder().text(s).requestContact(true).build());
                } else
                    row.add(s);
            }
        }
        return markup;
    }




    public ReplyKeyboardMarkup replyMaker(List<String> buttonLabels) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();

        for (int i = 0; i < buttonLabels.size(); i++) {
            if (i % 2 == 0 && !row.isEmpty()) {
                keyboard.add(row);
                row = new KeyboardRow();
            }
            row.add(new KeyboardButton(buttonLabels.get(i)));
        }
        if (!row.isEmpty()) {
            keyboard.add(row);
        }

        replyKeyboardMarkup.setKeyboard(keyboard);
        return replyKeyboardMarkup;
    }
}
