package uz.project.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import uz.project.models.Language;
import uz.project.models.Order;
import uz.project.models.Product;
import uz.project.models.SpecialCategory;

import java.util.ArrayList;
import java.util.List;

public class BotKeyboards {
    public static void setMainManuKeyboard(SendMessage sendMessage, Language language) {
        ReplyKeyboardMarkup replyKeyboardMarkup;
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

    public static void setShareContactKeyboardButton(SendMessage sendMessage, String text) {
        ReplyKeyboardMarkup replyKeyboardMarkup;
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

    public static void setInlineKeyboardButtonForSubCategories(SendMessage sendMessage, List<SpecialCategory> list) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();

        for (SpecialCategory specialCategory : list) {
            var button = new InlineKeyboardButton();
            button.setText(specialCategory.getName());
            button.setCallbackData("special_product_" + specialCategory.getId());

            inlineKeyboardButtonList.add(button);
            inlineButtons.add(inlineKeyboardButtonList);
            inlineKeyboardButtonList = new ArrayList<>();

        }

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }

    public static void setInlineKeyboardButtonForOrders(SendMessage sendMessage, List<Order> list) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {

            var button = new InlineKeyboardButton();

            button.setText(Integer.toString(i + 1));
            button.setCallbackData("order_" + list.get(i).getId());

            inlineKeyboardButtonList.add(button);

            if (Math.floorMod(i, 3) == 2) {
                inlineButtons.add(inlineKeyboardButtonList);
                inlineKeyboardButtonList = new ArrayList<>();
            }

            if (i == list.size() - 1 && Math.floorMod(i, 3) != 2) inlineButtons.add(inlineKeyboardButtonList);

        }

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }

    public static void setInlineKeyboardButtonForProduct(SendPhoto sendMessage, Product product, Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList3 = new ArrayList<>();

        var text1 = "Muddatli to'lov";
        var text2 = "Buyurtma berish";
        var text3 = "Savatchaga qo'shish";

        switch (language.toString()) {
            case "RUSSIAN": {
                text1 = "Срочный платеж";
                text2 = "Заказ";
                text3 = "Добавить в корзину";
            }
            break;
            case "ENGLISH": {
                text1 = "Term payment";
                text2 = "Order";
                text3 = "Add to basket";
            }
            break;
            case "KRILL": {
                text1 = "Муддатли тўлов";
                text2 = "Буюртма бериш";
                text3 = "Саватчага қўшиш";
            }
            break;
        }
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(text1);
        inlineKeyboardButton1.setCallbackData("periodic_payment_" + product.getId());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(text2);
        inlineKeyboardButton2.setCallbackData("ordering_product_" + product.getId());

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(text3);
        inlineKeyboardButton3.setCallbackData("add_basket_" + product.getId());

        inlineKeyboardButtonList1.add(inlineKeyboardButton1);
        inlineKeyboardButtonList2.add(inlineKeyboardButton2);
        inlineKeyboardButtonList3.add(inlineKeyboardButton3);

        inlineButtons.add(inlineKeyboardButtonList1);
        inlineButtons.add(inlineKeyboardButtonList2);
        inlineButtons.add(inlineKeyboardButtonList3);

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }

    public static void setInlineKeyboardButtonForBaskets(SendMessage sendMessage, List<Product> list, boolean isBasket) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            var button = new InlineKeyboardButton();
            button.setText(Integer.toString(i + 1));

            if (isBasket)
                button.setCallbackData("basket_product_" + list.get(i).getId());
            else
                button.setCallbackData("simple_product_" + list.get(i).getId());

            inlineKeyboardButtonList.add(button);

            if (Math.floorMod(i, 3) == 2) {
                inlineButtons.add(inlineKeyboardButtonList);
                inlineKeyboardButtonList = new ArrayList<>();
            }

            if (i == list.size() - 1 && Math.floorMod(i, 3) != 2) inlineButtons.add(inlineKeyboardButtonList);

        }

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }

    public static void setInlineKeyboardButtonForCredit(SendMessage sendMessage, Product product, Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList1 = new ArrayList<>();
        List<InlineKeyboardButton> inlineKeyboardButtonList2 = new ArrayList<>();

        var text1 = "3 oy";
        var text2 = "6 oy";
        var text3 = "9 oy";
        var text4 = "12 oy";

        switch (language.toString()) {
            case "RUSSIAN": {
                text1 = "3 месяца";
                text2 = "6 месяца";
                text3 = "9 месяца";
                text4 = "12 месяца";
            }
            break;
            case "ENGLISH": {
                text1 = "3 months";
                text2 = "6 months";
                text3 = "9 months";
                text4 = "12 months";
            }
            break;
            case "KRILL": {
                text1 = "3 ой";
                text2 = "6 ой";
                text3 = "9 ой";
                text4 = "12 ой";
            }
            break;
        }
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText(text1);
        inlineKeyboardButton1.setCallbackData("credit_3_" + product.getId());

        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton2.setText(text2);
        inlineKeyboardButton2.setCallbackData("credit_6_" + product.getId());

        InlineKeyboardButton inlineKeyboardButton3 = new InlineKeyboardButton();
        inlineKeyboardButton3.setText(text3);
        inlineKeyboardButton3.setCallbackData("credit_9_" + product.getId());

        InlineKeyboardButton inlineKeyboardButton4 = new InlineKeyboardButton();
        inlineKeyboardButton4.setText(text4);
        inlineKeyboardButton4.setCallbackData("credit_12_" + product.getId());

        inlineKeyboardButtonList1.add(inlineKeyboardButton1);
        inlineKeyboardButtonList1.add(inlineKeyboardButton2);
        inlineKeyboardButtonList2.add(inlineKeyboardButton3);
        inlineKeyboardButtonList2.add(inlineKeyboardButton4);

        inlineButtons.add(inlineKeyboardButtonList1);
        inlineButtons.add(inlineKeyboardButtonList2);

        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

    }
}
