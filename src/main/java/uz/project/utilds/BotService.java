package uz.project.utilds;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.bot.FurnitureBot;
import uz.project.models.Language;

import java.util.ArrayList;
import java.util.List;

public class BotService {

    public static void setLanguageKeyboardButton(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText("Uzbek");

        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText("English");

        KeyboardButton keyboardButton3 = new KeyboardButton();
        keyboardButton3.setText("Russian");

        KeyboardButton keyboardButton4 = new KeyboardButton();
        keyboardButton4.setText("Krill");

        keyboardRow1.add(keyboardButton);
        keyboardRow1.add(keyboardButton3);
        keyboardRow1.add(keyboardButton2);
        keyboardRow1.add(keyboardButton4);

        keyboardRows.add(keyboardRow1);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    public static void setLanguage(FurnitureBot furnitureBot, String language) {
        if (language.equals("Uzbek")) {
            furnitureBot.setLanguage(Language.UZBEK);

        } else if (language.equals("Russian")) {
            furnitureBot.setLanguage(Language.RUSSIAN);

        } else if (language.equals("English")) {
            furnitureBot.setLanguage(Language.ENGLISH);

        } else if (language.equals("Krill")) {
            furnitureBot.setLanguage(Language.KRILL);
        }

        System.out.println(furnitureBot.getCurrentChatId() + " --> " + furnitureBot.getLanguage());

    }

}
