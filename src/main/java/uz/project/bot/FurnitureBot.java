package uz.project.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.project.controllers.BotController;
import uz.project.models.*;
import uz.project.models.User;

import java.util.*;


@Service
public class FurnitureBot extends TelegramLongPollingBot {

    private static int messageType = 0;
    private boolean isStart = false;
    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;
    private boolean isName = false;
    private boolean isSurname = false;
    private Product product;
    private User user;

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
            instUser(message);

            if (message.hasText()) {
                String text = message.getText();

                if (text.equals("/start")) {
                    BotService.startBot(this, message);
                    user = botController.getUserByChatId(currentChatId);
                    isName = false;
                    isSurname = false;
                    language = Language.UZBEK;

                } else if (text.equals("user")) {
                    BotService.sendMessage(this, message, botController.getUserByChatId(currentChatId).toString());

                } else if (text.equals("Kategory") || text.equals("Категория") || text.equals("Category") || text.equals("Категорйa")) {
                    BotService.sendMessageForCategories(this, message, language);

                } else if (text.equals("Buyurtmalar") || text.equals("Заказы") || text.equals("Orders") || text.equals("Буюртмалар")) {
                    BotService.sendOrdersMessage(this, message);

                } else if (text.equals("Savatcha") || text.equals("Корзина") || text.equals("Basket") || text.equals("Савадча")) {
                    Set<Product> products;
                    products = user.getBasketProducts();

                    if (products != null && !products.isEmpty()) {
                        var list = new ArrayList<>(products);
                        BotService.sendBasketProductsMessage(this, message, user, language, list);
                    } else {
                        BotService.sendMessageForEmptyProductList(this, message, language);
                    }

                } else if (text.equals("Profil") || text.equals("Профиль") || text.equals("Profile") || text.equals("Профил")) {
                    BotService.sendMessageForProfile(this, message, language, user);

                } else if (text.equals("Orqaga") || text.equals("Назад") || text.equals("Back") || text.equals("Орқага")) {
                    BotService.sendMessageForSharingContact(this, message, "Orqaga", language);

                } else if (isName) {
                    BotService.sendMessage(this, message, message.getText());
                    var name = message.getText();
                    if (name.length() > 4) {
                        isName = false;

                        if (user.getId() == -1)
                            user.setName(message.getText());

                        BotService.askUsersSurname(this, language, message);
                    } else {
                        BotService.askUsersName(this, language, message);
                    }

                } else if (isSurname) {
                    BotService.sendMessage(this, message, message.getText());
                    if (message.getText().length() > 4) {
                        isSurname = false;

                        if (user.getId() == -1)
                            user.setSurname(message.getText());

                        var value = BotService.checkUserOrCreate(this, user, message);

                        if (value)
                            BotService.sendMessageForMainMenu(this, message, "", language);
                        else
                            BotService.sendMessage(this, message, "Not Allowed !");

                    } else {
                        BotService.askUsersSurname(this, language, message);
                    }
                } else if (isStart) {
                    messageType++;

                    if (messageType < 10) {
                        BotService.senMessageForPosition(this, message, language, messageType);
                    } else {
                        isStart = false;
                        BotService.sendMessage(this, message, "Should be payment ........");
                        messageType = 0;
                    }
                }

            } else if (message.hasContact()) {
                var contact = message.getContact();
                user.setPhoneNumber(contact.getPhoneNumber());

                BotService.sendMessage(this, message, contact.toString());
                BotService.askUsersName(this, language, message);

            } else if (message.hasPhoto()) {
                if (isStart) {
                    messageType++;

                    if (messageType < 10) {
                        BotService.senMessageForPosition(this, message, language, messageType);
                    } else {
                        isStart = false;
                        BotService.sendMessage(this, message, "Should be payment ........");
                        messageType = 0;
                    }
                }
            }

            Location location = message.getLocation();

            if (location != null) {
                BotService.sendMessageForMainMenu(this, message, "", language);
                BotService.orderProduct(this, message, user, language, location, product);

            }
        } else if (update.hasCallbackQuery()) {
            Message message = update.getCallbackQuery().getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();
            instUser(message);

            if (data.equals("Uzbek") || data.equals("Russian") || data.equals("English") || data.equals("Krill")) {
                BotService.setLanguage(this, data);
                BotService.showChosenLanguage(this, message, data);

            } else if (data.startsWith("order_")) {
                var id = Long.valueOf(data.substring(6));
                var order = botController.getOrderWithId(id);

                if (order != null) BotService.sendMessageForOrderDescription(this, message, order);

            } else if (data.startsWith("ordering_")) {
                product = null;
                BotService.shareLocation(this, message, language);

            } else if (data.startsWith("basket_product_")) {
                var id = Long.valueOf(data.substring(15));
                var product = botController.getProductWithID(id);
                BotService.sendMessageForBasketProduct(this, message, product, user, language);

            } else if (data.startsWith("simple_product_")) {
                var productId = Long.valueOf(data.substring(15));
                var product = botController.getProductWithID(productId);

                if (product != null)
                    BotService.sendMessageForProductDescription(this, message, product, user, language, false);

            } else if (data.startsWith("delete_from_basket_")) {
                var productId = Long.valueOf(data.substring(19));
                var product = botController.getProductWithID(productId);

                if (product != null) {
                    try {
                        botController.saveUser(user);
                        user = botController.getUserWithId(user.getId());
                        BotService.sendMessage(this, message, "Deleted");
                    } catch (Exception e) {
                        e.printStackTrace();
                        BotService.sendMessage(this, message, "Deleted");
                    }
                } else {
                    BotService.sendMessageForEmptyProductList(this, message, language);
                }


            } else if (data.equals("FURNITURE") || data.equals("HOME_APPLIANCE") || data.equals("TELEPHONE_AND_ACCESSORY") || data.equals("LAPTOPS_AND_DESKTOPS") || data.equals("CARPETS") || data.equals("AUDIO_APPLIANCE") || data.equals("SPORTS_EQUIPMENTS") || data.equals("GIFTS_AND_SOUVENIRS") || data.equals("KITCHEN_APPLIANCE")) {
                List<SpecialCategory> categories = botController.getAllSubCategories(data);
                BotService.sendMessageForSubCategories(this, message, categories);

            } else if (data.startsWith("special_product_")) {
                var id = Long.valueOf(data.substring(16));
                var spCategory = botController.getSpecialCategoryWithID(id);

                if (spCategory != null) {
                    var list = botController.getAllProductsOfSubcategory(spCategory);

                    if (list != null && list.size() != 0) {
                        BotService.sendProductsListMessage(this, message, user, language, list);
                    } else {
                        BotService.sendMessageForEmptyProductList(this, message, language);
                    }
                }

            } else if (data.startsWith("periodic_payment_")) {
                var productId = Long.valueOf(data.substring(17));
                var product = botController.getProductWithID(productId);
                BotService.sendMessageForProductTermPayment(this, message, product, language);
                //Todo(payment)

            } else if (data.startsWith("orderingProduct_")) {
                product = null;
                var productId = Long.valueOf(data.substring(16));
                product = botController.getProductWithID(productId);
                BotService.shareLocation(this, message, language);

            } else if (data.startsWith("add_basket_")) {
                var productId = Long.valueOf(data.substring(11));
                var product = botController.getProductWithID(productId);

                if (product != null) {
                    var bool = user.addProductToBasket(product);
                    if (bool) {
                        botController.saveUser(user);
                        BotService.sendMessageForAddingProduct(this, message, user.getLanguage());
                    } else {
                        BotService.sendMessageForEmptyProductList(this, message, user.getLanguage());
                    }
                } else {
                    BotService.sendMessageForEmptyProductList(this, message, user.getLanguage());
                }

            } else if (data.equals("share_contact")) {
                BotService.sendMessage(this, message, "Contact...");

            } else if (data.startsWith("credit_3_")) {
                var productId = Long.valueOf(data.substring(9));
                isStart = true;
                BotService.senMessageForPosition(this, message, language, messageType);

            } else if (data.startsWith("credit_6_")) {
                var productId = Long.valueOf(data.substring(9));
                isStart = true;
                BotService.senMessageForPosition(this, message, language, messageType);

            } else if (data.startsWith("credit_9_")) {
                var productId = Long.valueOf(data.substring(9));
                isStart = true;
                BotService.senMessageForPosition(this, message, language, messageType);

            } else if (data.startsWith("credit_12_")) {
                var productId = Long.valueOf(data.substring(10));
                isStart = true;
                BotService.senMessageForPosition(this, message, language, messageType);

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

    public void setSurname(boolean surname) {
        isSurname = surname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BotController getBotController() {
        return botController;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void sendMessage(Message message, SendMessage sendMessage) {
        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


    public void sendMessagePhoto(Message message, SendPhoto sendMessage) {
        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void instUser(Message message) {
        this.currentChatId = message.getChatId();

        if (user == null) {
            user = botController.getUserByChatId(currentChatId);
            user.setChatId(currentChatId);
            messageType = 0;

        } else if (!Objects.equals(user.getChatId(), currentChatId)) {
            user = botController.getUserByChatId(currentChatId);
            user.setChatId(currentChatId);
            messageType = 0;
        }

        if (user.getId() != -1L && user.getLanguage() != null)
            language = user.getLanguage();
    }
}
