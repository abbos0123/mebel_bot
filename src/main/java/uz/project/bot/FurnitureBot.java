package uz.project.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.project.controllers.BotController;
import uz.project.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class FurnitureBot extends TelegramLongPollingBot {
    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;

    private List<Order> list = new ArrayList<>();
    private List<Product> list2 = new ArrayList<>();
    private boolean isName = false;
    private boolean isSurname = false;

    @Autowired
    private BotController botController;

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;


    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }


    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            Message message = update.getMessage();
            this.currentChatId = message.getChatId();

            if (message.hasText()) {
                String text = message.getText();

                if (text.equals("/start")) {
                    BotService.startBot(this, message);
                    System.out.println();

                } else if (text.equals("Kategory") || text.equals("Категория") || text.equals("Category") || text.equals("Категорйa")) {
                    BotService.sendMessageForCategories(this, message, language);

                } else if (text.equals("Buyurtmalar") || text.equals("Заказы") || text.equals("Orders") || text.equals("Буюртмалар")) {
                    list = new ArrayList<>();
                    for (int i = 0; i < 13; i++) {
                        var order = new Order("" + (i + 1) + ") Order_name", "Description");
                        list.add(order);
                    }
                    BotService.sendOrdersMessage(this, message, list);


                } else if (text.equals("Savatcha") || text.equals("Корзина") || text.equals("Basket") || text.equals("Савадча")) {
                    list2 = new ArrayList<>();
                    for (int i = 0; i < 13; i++) {
                        var product = new Product("" + (i + 1) + ") basket_product_name", (long) i);
                        list2.add(product);
                    }
                    BotService.sendBasketProductsMessage(this, message, list2);

                } else if (text.equals("Profil") || text.equals("Профиль") || text.equals("Profile") || text.equals("Профил")) {
                    BotService.sendMessageForProfile(this, message, language, new User());

                } else if (text.equals("Orqaga") || text.equals("Назад") || text.equals("Back") || text.equals("Орқага")) {
                    BotService.sendMessageForSharingContact(this, message, "Orqaga", language);


                } else if (isName) {

                    BotService.sendMessage(this, message, message.getText());
                    isName = false;
                    BotService.askUsersSurname(this, language, message);

                } else if (isSurname) {

                    BotService.sendMessage(this, message, message.getText());
                    isSurname = false;
                    BotService.sendMessage(this, message, "Siz mumfaqqiyatli o'tdingiz...");
                    BotService.sendMessageForMainMenu(this, message, "", language);

                }

            } else if (message.hasContact()) {
                var contact = message.getContact();
                BotService.sendMessage(this, message, contact.toString());
                BotService.askUsersName(this, language, message);

            }

        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            if (data.equals("Uzbek") || data.equals("Russian") || data.equals("English") || data.equals("Krill")) {

                BotService.setLanguage(this, data);
                BotService.showChosenLanguage(this, message, data);

            } else if (data.startsWith("order_")) {

                Order order = new Order();
                var id = Long.valueOf(data.substring(6));
                for (Order order1 : list) {
                    if (Objects.equals(order1.getId(), id)) {
                        order = order1;
                        break;
                    }
                }

                BotService.sendMessage(this, message, order.toString());

            } else if (data.startsWith("basket_product_")) {

                Product product = new Product();
                var id = Long.valueOf(data.substring(15));
                for (Product product1 : list2) {
                    if (Objects.equals(product1.getId(), id)) {
                        product = product1;
                        break;
                    }
                }
                BotService.sendMessage(this, message, product.toString());

            } else if (data.equals("FURNITURE") || data.equals("HOME_APPLIANCE") || data.equals("TELEPHONE_AND_ACCESSORY") || data.equals("LAPTOPS_AND_DESKTOPS") || data.equals("CARPETS") || data.equals("AUDIO_APPLIANCE") || data.equals("SPORTS_EQUIPMENTS") || data.equals("GIFTS_AND_SOUVENIRS") || data.equals("KITCHEN_APPLIANCE")) {
                List<SpecialCategory> categories = botController.getAllSubCategories(data);
                BotService.sendMessageForSubCategories(this, message, categories);
            }


            if (data.equals("share_contact")) {
                BotService.sendMessage(this, message, "Contact.....");
            }

        }
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Long getCurrentChatId() {
        return currentChatId;
    }

    public boolean isName() {
        return isName;
    }

    public void setName(boolean name) {
        isName = name;
    }

    public boolean isSurname() {
        return isSurname;
    }

    public void setSurname(boolean surname) {
        isSurname = surname;
    }

    public void setCurrentChatId(Long currentChatId) {
        this.currentChatId = currentChatId;
    }


    public void sendMessage(Message message, SendMessage sendMessage) {
        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
