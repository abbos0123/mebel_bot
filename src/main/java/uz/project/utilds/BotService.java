package uz.project.utilds;


import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.bot.FurnitureBot;
import uz.project.models.Language;
import uz.project.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class BotService {
    public static ReplyKeyboardMarkup replyKeyboardMarkup;

    public static void setMainManuKeyboard(SendMessage sendMessage, Language language) {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();
        KeyboardRow keyboardRow2 = new KeyboardRow();
        KeyboardRow keyboardRow3 = new KeyboardRow();

        KeyboardButton keyboardButton1 = new KeyboardButton();
        KeyboardButton keyboardButton2 = new KeyboardButton();
        KeyboardButton keyboardButton3 = new KeyboardButton();
        KeyboardButton keyboardButton4 = new KeyboardButton();
        KeyboardButton keyboardButton5 = new KeyboardButton();

        switch (language.toString()) {
            case "UZBEK": {
                keyboardButton1.setText("Kategory");
                keyboardButton2.setText("Buyurtmalar");
                keyboardButton3.setText("Savatcha");
                keyboardButton4.setText("Profil");
                keyboardButton5.setText("Orqaga");
            }
            break;
            case "RUSSIAN": {
                keyboardButton1.setText("Категория");
                keyboardButton2.setText("Заказы");
                keyboardButton3.setText("Корзина");
                keyboardButton4.setText("Профиль");
                keyboardButton5.setText("Назад");
            }
            break;
            case "ENGLISH": {
                keyboardButton1.setText("Category");
                keyboardButton2.setText("Orders");
                keyboardButton3.setText("Basket");
                keyboardButton4.setText("Profile");
                keyboardButton5.setText("Back");
            }
            break;
            case "KRILL": {
                keyboardButton1.setText("Категорйa");
                keyboardButton2.setText("Буюртмалар");
                keyboardButton3.setText("Савадча");
                keyboardButton4.setText("Профил");
                keyboardButton5.setText("Орқага");
            }
            break;
        }
        keyboardRow1.add(keyboardButton1);
        keyboardRow1.add(keyboardButton3);
        keyboardRow2.add(keyboardButton2);
        keyboardRow2.add(keyboardButton4);
        keyboardRow3.add(keyboardButton5);

        keyboardRows.add(keyboardRow1);
        keyboardRows.add(keyboardRow2);
        keyboardRows.add(keyboardRow3);

        replyKeyboardMarkup.setKeyboard(keyboardRows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }

    private static void setShareContactKeyboardButton(SendMessage sendMessage, String text) {
        replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow keyboardRow1 = new KeyboardRow();

        KeyboardButton keyboardButton = new KeyboardButton();
        keyboardButton.setText(text);
        keyboardButton.setRequestContact(true);

        keyboardRow1.add(keyboardButton);

        keyboardRows.add(keyboardRow1);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        sendMessage.setReplyMarkup(replyKeyboardMarkup);
    }


    public static void setInlineKeyboardButtonForLanguage(SendMessage sendMessage) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();

        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Uzbek");
        inlineKeyboardButton1.setCallbackData("Uzbek");

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText("Russian");
        inlineKeyboardButton2.setCallbackData("Russian");

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText("English");
        inlineKeyboardButton3.setCallbackData("English");

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText("Krill");
        inlineKeyboardButton4.setCallbackData("Krill");

        inlineKeyboardButtonList.add(inlineKeyboardButton1);
        inlineKeyboardButtonList.add(inlineKeyboardButton2);
        inlineKeyboardButtonList2.add(inlineKeyboardButton3);
        inlineKeyboardButtonList2.add(inlineKeyboardButton4);

        inlineButtons.add(inlineKeyboardButtonList);
        inlineButtons.add(inlineKeyboardButtonList2);

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }



    public static void sendMessage(FurnitureBot furnitureBot, Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForLanguage(FurnitureBot furnitureBot, Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        BotService.setInlineKeyboardButtonForLanguage(sendMessage);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForCategories(FurnitureBot furnitureBot, Message message, Language language, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        BotServiceHelper.setInlineKeyboardButtonForProductCategories(sendMessage, language);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForMainMenu(FurnitureBot furnitureBot, Message message, String text, Language language) {
        SendMessage sendMessage = new SendMessage();
        switch (language.toString()) {
            case "UZBEK":
                sendMessage.setText("Asosiy Menyu:");
                break;
            case "RUSSIAN":
                sendMessage.setText("Главное меню:");
                break;
            case "KRILL":
                sendMessage.setText("Aсосий Меню:");
                break;
            case "ENGLISH":
                sendMessage.setText("Main menu:");
                break;
            default:
                sendMessage.setText("Asosiy Menyu:");
                break;
        }
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        BotService.setMainManuKeyboard(sendMessage, language);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void showChosenLanguage(FurnitureBot furnitureBot, Message message, String text) {
        switch (text) {
            case "Uzbek": {
                BotService.sendMessage(furnitureBot, message, "Siz Uzbek tilini tanladingiz :-)");
                BotService.sendMessageForSharingContact(furnitureBot, message, "Iltimos kontaktingizni yuboring...", Language.UZBEK);
            }
            break;

            case "English": {
                BotService.sendMessage(furnitureBot, message, "\n\nYou have chosen English :-)\n\n");
                BotService.sendMessageForSharingContact(furnitureBot, message, "Please send us your contact...", Language.ENGLISH);
                System.out.println("LAN: " + Language.ENGLISH);
            }
            break;

            case "Russian": {
                BotService.sendMessage(furnitureBot, message, "Вы выбрали русский :-)");
                BotService.sendMessageForSharingContact(furnitureBot, message, "пожалуйста, пришлите свой контакт...", Language.RUSSIAN);
                System.out.println("LAN: " + Language.RUSSIAN);
            }
            break;

            case "Krill": {
                BotService.sendMessage(furnitureBot, message, "Сиз Узбек тилини танладингиз :-)");
                sendMessageForSharingContact(furnitureBot, message, "Илтимос контактингизни юборинг...", Language.KRILL);
                System.out.println("LAN: " + Language.KRILL);
            }
            break;


            default: {
                sendMessageForSharingContact(furnitureBot, message, "Siz Uzbek tilini tanladingiz :-).", Language.UZBEK);
            }
            break;
        }
    }

    public static void sendMessageForSharingContact(FurnitureBot furnitureBot, Message message, String text, Language language) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        String myText = "Share contact";

        if (language.equals(Language.UZBEK))
            myText = "Kontaktni ulashish";

        else if (language.equals(Language.RUSSIAN))
            myText = "Обмен контактами";

        else if (language.equals(Language.KRILL))
            myText = "Контаcтни улашиш";


        BotService.setShareContactKeyboardButton(sendMessage, myText);

        furnitureBot.sendMessage(message, sendMessage);
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

    public static void askUsersName(FurnitureBot furnitureBot, Language language, Message message) {
        switch (String.valueOf(language)) {
            case "UZBEK": {
                BotService.sendMessage(furnitureBot, message, "Ismingizni kiriting...");
                furnitureBot.setName(true);
            }
            break;
            case "KRILL": {
                BotService.sendMessage(furnitureBot, message, "Исмингизни киритинг...");
                furnitureBot.setName(true);
            }
            break;
            case "ENGLISH": {
                BotService.sendMessage(furnitureBot, message, "Insert your name...");
                furnitureBot.setName(true);
            }
            break;
            case "RUSSIAN": {
                BotService.sendMessage(furnitureBot, message, "Введите ваше имя ...");
                furnitureBot.setName(true);
            }
            break;
            default:
                BotService.sendMessage(furnitureBot, message, "Ismingizni kiriting....");
                furnitureBot.setName(true);
                break;
        }
    }

    public static void askUsersSurname(FurnitureBot furnitureBot, Language language, Message message) {
        switch (String.valueOf(language)) {
            case "UZBEK": {
                BotService.sendMessage(furnitureBot, message, "Familiyangizni kiriting.");
                furnitureBot.setSurname(true);
            }
            break;
            case "KRILL": {
                BotService.sendMessage(furnitureBot, message, "Фамилиянгизни киритинг...");
                furnitureBot.setSurname(true);
            }
            break;
            case "ENGLISH": {
                BotService.sendMessage(furnitureBot, message, "Inser your surname ...");
                furnitureBot.setSurname(true);
            }
            break;
            case "RUSSIAN": {
                BotService.sendMessage(furnitureBot, message, "Введите вашу фамилию....");
                furnitureBot.setSurname(true);
            }
            break;
            default:
                BotService.sendMessage(furnitureBot, message, "Familiyangizni kiriting....");
                furnitureBot.setSurname(true);
                break;
        }
    }
}
