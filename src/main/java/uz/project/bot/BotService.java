package uz.project.bot;


import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import uz.project.models.*;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BotService {

    private static StringBuilder stringBuilder = new StringBuilder();

    public static void startBot(FurnitureBot furnitureBot, Message message) {
        BotService.sendMessageForLanguage(furnitureBot, message, " Uzbek    ->   Iltimos tilni tanlang" +
                "\nРусский  ->  Пожалуйста, выберите язык " +
                "\nEnglish ->  Please choose Language " +
                "\nКрилл    ->  Илтимос тилни танланг");
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

        BotKeyboards.setInlineKeyboardButtonForLanguage(sendMessage);
        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForCategories(FurnitureBot furnitureBot, Message message, Language language) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        switch (language.toString()) {
            case "UZBEK": {
                sendMessage.setText("Tur bo'yicha maxsulotlarni saralashingz mumkin :-)");
            }
            break;
            case "ENGLISH": {
                sendMessage.setText("You can sort products by type :-) ");
            }
            break;
            case "RUSSIAN": {
                sendMessage.setText("Вы можете сортировать товары по типу :-)");
            }
            break;
            case "KRILL": {
                sendMessage.setText("Тур бўйича махсулотларни саралашингз мумкин :-)");
            }
            break;
        }
        BotServiceHelper.setInlineKeyboardButtonForProductCategories(sendMessage, language);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForMainMenu(FurnitureBot furnitureBot, Message message, String text, Language language) {
        SendMessage sendMessage = new SendMessage();
        switch (language.toString()) {
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
        BotKeyboards.setMainManuKeyboard(sendMessage, language);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void showChosenLanguage(FurnitureBot furnitureBot, Message message, String text) {
        switch (text) {
            case "Uzbek": {
                BotService.sendMessage(furnitureBot, message, "Siz Uzbek tilini tanladingiz :-)");
                BotService.sendMessageForSharingContact(furnitureBot, message, "Iltimos kontaktingizni yuboring...", Language.UZBEK);
                furnitureBot.getUser().setLanguage(Language.UZBEK);
            }
            break;
            case "English": {
                BotService.sendMessage(furnitureBot, message, "\n\nYou have chosen English :-)\n\n");
                BotService.sendMessageForSharingContact(furnitureBot, message, "Please send us your contact...", Language.ENGLISH);
                System.out.println("LAN: " + Language.ENGLISH);
                furnitureBot.getUser().setLanguage(Language.ENGLISH);
            }
            break;
            case "Russian": {
                BotService.sendMessage(furnitureBot, message, "Вы выбрали русский :-)");
                BotService.sendMessageForSharingContact(furnitureBot, message, "пожалуйста, пришлите свой контакт...", Language.RUSSIAN);
                System.out.println("LAN: " + Language.RUSSIAN);
                furnitureBot.getUser().setLanguage(Language.RUSSIAN);

            }
            break;
            case "Krill": {
                BotService.sendMessage(furnitureBot, message, "Сиз Узбек тилини танладингиз :-)");
                sendMessageForSharingContact(furnitureBot, message, "Илтимос контактингизни юборинг...", Language.KRILL);
                System.out.println("LAN: " + Language.KRILL);
                furnitureBot.getUser().setLanguage(Language.KRILL);
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

        if (language.equals(Language.UZBEK)) myText = "Kontaktni ulashish";
        else if (language.equals(Language.RUSSIAN)) myText = "Обмен контактами";
        else if (language.equals(Language.KRILL)) myText = "Контаcтни улашиш";

        BotKeyboards.setShareContactKeyboardButton(sendMessage, myText, false);
        furnitureBot.sendMessage(message, sendMessage);
    }


    public static void sendOrdersMessage(FurnitureBot furnitureBot, Message message) {
        var list = furnitureBot.getBotController().getAllOrdersOfUser(furnitureBot.getUser().getId());
        if (list == null || list.isEmpty()) list = new ArrayList<>();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        if (list.isEmpty()) {
            var txt = "Hech qanday buyurtma mavjud emas :-|";
            switch (furnitureBot.getLanguage().toString()) {
                case "KRILL": {
                    txt = "Ҳеч қандай буюртма мавжуд емас :-|";
                }
                break;
                case "RUSSIAN": {
                    txt = "Заказ недоступен :-|";
                }
                break;
                case "ENGLISH": {
                    txt = "No order available :-|";
                }
                break;
            }
            sendMessage.setText(txt);
            furnitureBot.sendMessage(message, sendMessage);
            return;
        }

        var stringBuilder = new StringBuilder();
        var stringBuilder2 = new StringBuilder();

        for (Order order : list) {
            var text = order.getName() + " (" + order.getOrderType() + ")\n" +
                    "Sum: " + order.getTotalPrice() + "\n\n";

            if (order.getOrderType().equals(OrderType.ACTIVE))
                stringBuilder.append(text);
            else
                stringBuilder2.append(text);

        }

        var msgText = stringBuilder.append("\n__________\n").append(stringBuilder2).toString();
        sendMessage.setText(msgText);
        BotKeyboards.setInlineKeyboardButtonForOrders(sendMessage, list);
        furnitureBot.sendMessage(message, sendMessage);

    }

    public static void sendBasketProductsMessage(FurnitureBot furnitureBot, Message message, User user, Language language, List<Product> list) {
        if (list == null || list.isEmpty()) list = new ArrayList<>();
        stringBuilder = new StringBuilder();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        int pos = 1;
        double totalPrice = 0d;

        for (Product product : list) {
            var text = pos + ") " + product.getName() + "\n\n";
            stringBuilder.append(text);
            totalPrice += product.getPrice();
            pos++;
        }

        stringBuilder.append("\nSUM:  ").append(totalPrice);

        var msgText = stringBuilder.toString();

        if (totalPrice != 0D) {
            sendMessage.setText(msgText);
            BotKeyboards.setInlineKeyboardButtonForBaskets(sendMessage, list, user, language, true);
            furnitureBot.sendMessage(message, sendMessage);
        } else {
            var txt = "Savatchada maxsulto mavjud emas :-(";
            switch (language.toString()) {
                case "RUSSIAN": {
                    txt = "Саватчада макссулто мавджуд эмас :-(";
                }
                break;
                case "ENGLISH": {
                    txt = "Basket is empty :-(";
                }
                break;
                case "KRILL": {
                    txt = "Саватчада махсулто мавжуд емас :-(";
                }
                break;
            }
            sendMessage.setText(txt);
            furnitureBot.sendMessage(message, sendMessage);
        }
    }

    public static void sendProductsListMessage(FurnitureBot furnitureBot, Message message, User user, Language language, List<Product> list) {
        stringBuilder = new StringBuilder();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());

        for (int i = 0; i < list.size(); i++) {
            var text = list.get(i).getName() + "\n\n";
            stringBuilder.append(i + 1).append(") ").append(text);
        }

        var msgText = stringBuilder.toString();
        sendMessage.setText(msgText);
        BotKeyboards.setInlineKeyboardButtonForBaskets(sendMessage, list, user, language, false);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForEmptyProductList(FurnitureBot furnitureBot, Message message, Language language) {
        switch (language.toString()) {
            case "UZBEK": {
                BotService.sendMessage(furnitureBot, message, "Afsuskiy mahsulot mavjud emas :-(");
            }
            break;
            case "RUSSIAN": {
                BotService.sendMessage(furnitureBot, message, "К сожалению, товар недоступен :-(");
            }
            break;
            case "ENGLISH": {
                BotService.sendMessage(furnitureBot, message, "Unfortunately the product is not available :-(");
            }
            break;
            case "KRILL": {
                BotService.sendMessage(furnitureBot, message, "Aфсуский маҳсулот мавжуд емас :-(");
            }
            break;
        }
    }

    public static void sendMessageForAddingProduct(FurnitureBot furnitureBot, Message message, Language language) {
        switch (language.toString()) {
            case "UZBEK": {
                BotService.sendMessage(furnitureBot, message, "Maxsulto sizning savatchangizga qo'shildi :-)");
            }
            break;
            case "RUSSIAN": {
                BotService.sendMessage(furnitureBot, message, "К сожалению, товар недоступен :-(");
            }
            break;
            case "ENGLISH": {
                BotService.sendMessage(furnitureBot, message, "Not available product :-)\"");
            }
            break;
            case "KRILL": {
                BotService.sendMessage(furnitureBot, message, "Aфсуский маҳсулот мавжуд емас :-(");
            }
            break;
        }
    }


    public static void sendMessageForSubCategories(FurnitureBot furnitureBot, Message message, List<SpecialCategory> categories) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());
        var msgText = "Quyidagi kategorialar bo'yicha mashsulot topishingiz mumkin :-)";

        if (categories.size() > 0) {
            switch (furnitureBot.getLanguage().toString()) {
                case "RUSSIAN": {
                    msgText = "Вы можете найти товары в следующих категориях :-)";
                }
                break;
                case "ENGLISH": {
                    msgText = "You can find products in the following categories :-)";
                }
                break;
                case "KRILL": {
                    msgText = "Қуйидаги категориалар бўйича машсулот топишингиз мумкин :-)";
                }
                break;
            }
        } else {
            switch (furnitureBot.getLanguage().toString()) {

                case "UZBEK": {
                    msgText = "Afsuski mahsulot turi mavjud emas :-(";
                }
                break;

                case "RUSSIAN": {
                    msgText = "К сожалению, тип продукта недоступен :-(";
                }
                break;
                case "ENGLISH": {
                    msgText = "Unfortunately the product type is not available :-(";
                }
                break;
                case "KRILL": {
                    msgText = "Aфсуски маҳсулот тури мавжуд емас :-(";
                }
                break;
            }
        }

        sendMessage.setText(msgText);
        BotKeyboards.setInlineKeyboardButtonForSubCategories(sendMessage, categories);
        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForProfile(FurnitureBot furnitureBot, Message message, Language language, User user) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(message.getChatId().toString());
        stringBuilder = new StringBuilder();

        switch (language.toString()) {
            case "UZBEK": {
                stringBuilder.append("\nSizning shaxsiy ma'lumotlaringiz: \n\n\tIsm: ").append(user.getName()).append("\n\tFamiliya : ").append(user.getSurname()).append("\n\tTel : ").append(user.getPhoneNumber());
            }
            break;
            case "RUSSIAN": {
                stringBuilder.append("\nВаша личная информация: \n\n\tИмя: ").append(user.getName()).append("\n\tФамилия : ").append(user.getSurname()).append("\n\tТел : ").append(user.getPhoneNumber());
            }
            break;
            case "ENGLISH": {
                stringBuilder.append("\nYour personal information: \n\n\tName: ").append(user.getName()).append("\n\tSurname : ").append(user.getSurname()).append("\n\tTel : ").append(user.getPhoneNumber());
            }
            break;
            case "KRILL": {
                stringBuilder.append("\nСизнинг шахсий маълумотларингиз: \n\n\tИсм: ").append(user.getName()).append("\n\tФамилия : ").append(user.getSurname()).append("\n\tТел : ").append(user.getPhoneNumber());
            }
            break;
        }

        var msgText = stringBuilder.toString();
        sendMessage.setText(msgText);

        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForProductDescription(FurnitureBot furnitureBot, Message message, Product product, User user, Language language, Boolean isBasketProduct) {
        SendPhoto sendMessage = new SendPhoto();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        stringBuilder = new StringBuilder();

        switch (language.toString()) {
            case "UZBEK": {
                stringBuilder.append("\nMAXSULOT:\n");
                stringBuilder.append("\nNOMI: ").append(product.getName());
                stringBuilder.append("\n\nNARXI: ").append(product.getPrice());
                stringBuilder.append("\n\nMA'LUMOT: ").append(product.getDescription());
            }

            break;
            case "ENGLISH": {
                stringBuilder.append("\nPRODUCT:\n");
                stringBuilder.append("\nNAME: ").append(product.getName());
                stringBuilder.append("\n\nPRICE: ").append(product.getPrice());
                stringBuilder.append("\n\nINFORMATION: ").append(product.getDescription());
            }
            break;
            case "RUSSIAN": {
                stringBuilder.append("\nПРОДУКТ:\n");
                stringBuilder.append("\nНАИМЕНОВАНИЕ ТОВАРА: ").append(product.getName());
                stringBuilder.append("\n\nСТОИМОСТЬ: ").append(product.getPrice());
                stringBuilder.append("\n\nИНФОРМАЦИЯ: ").append(product.getDescription());
            }
            break;
            case "KRILL": {
                stringBuilder.append("\nМAХСУЛОТ:\n");
                stringBuilder.append("\nНОМИ: ").append(product.getName());
                stringBuilder.append("\n\nНAРХИ: ").append(product.getPrice());
                stringBuilder.append("\n\nМAъЛУМОТ: ").append(product.getDescription());
            }
            break;
        }

        var txt = stringBuilder.toString();

        //Todo(Sending message with own photo)

        sendMessage.setPhoto(new InputFile(new File(String.format("src\\\\main\\\\resources/%s", "upload_files/31bAZL.png")), "file_name"));
        sendMessage.setProtectContent(false);
        sendMessage.setCaption(txt);

        if (!isBasketProduct)
            BotKeyboards.setInlineKeyboardButtonForProduct(sendMessage, product, language);
        else
            BotKeyboards.setInlineKeyboardButtonForBasketDeleting(sendMessage, product, user, language);

        furnitureBot.sendMessagePhoto(message, sendMessage);
    }

    public static void sendMessageForBasketProduct(FurnitureBot furnitureBot, Message message, Product product, User user, Language language) {
        sendMessageForProductDescription(furnitureBot, message, product, user, language, true);
    }


    public static void sendMessageForProductTermPayment(FurnitureBot furnitureBot, Message message, Product product, Language language) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        stringBuilder = new StringBuilder();

        switch (language.toString()) {
            case "UZBEK": {
                stringBuilder.append("\nNOMI: ").append(product.getName());
                stringBuilder.append("\n\nNARXI: ").append(product.getPrice());
                stringBuilder.append("\n\n3-OYLIK KREDIT(20%) : Har oyida - > ").append(product.getPrice() * 1.2 / 3);
                stringBuilder.append("\n\n6-OYLIK KREDIT(30%) : Har oyida - > ").append(product.getPrice() * 1.3 / 6);
                stringBuilder.append("\n\n9-OYLIK KREDIT(40%) : Har oyida - > ").append(product.getPrice() * 1.4 / 9);
                stringBuilder.append("\n\n12-OYLIK KREDIT(20%) : Har oyida - > ").append(product.getPrice() * 1.5 / 12);
            }
            break;
            case "ENGLISH": {
                stringBuilder.append("\nNAME: ").append(product.getName());
                stringBuilder.append("\n\nPROCE: ").append(product.getPrice());
                stringBuilder.append("\n\n3 MONTH CREDIT(20%) : Each month - > ").append(product.getPrice() * 1.2 / 3);
                stringBuilder.append("\n\n6-MONTH CREDIT(30%) : Each month - > ").append(product.getPrice() * 1.3 / 6);
                stringBuilder.append("\n\n9-MONTH CREDIT(40%) : Each month - > ").append(product.getPrice() * 1.4 / 9);
                stringBuilder.append("\n\n12-MONTH CREDIT(20%) : Each month - > ").append(product.getPrice() * 1.5 / 12);
            }
            break;
            case "RUSSIAN": {
                stringBuilder.append("\nНАЗВАНИЕ: ").append(product.getName());
                stringBuilder.append("\n\nСТОИМОСТЬ: ").append(product.getPrice());
                stringBuilder.append("\n\nКРЕДИТ НА 3 МЕСЯЦА(20%) : Каждый месяц - > ").append(product.getPrice() * 1.2 / 3);
                stringBuilder.append("\n\nКРЕДИТ НА 6 МЕСЯЦА(30%) : Каждый месяц - > ").append(product.getPrice() * 1.3 / 6);
                stringBuilder.append("\n\nКРЕДИТ НА 9 МЕСЯЦА(40%) : Каждый месяц - > ").append(product.getPrice() * 1.4 / 9);
                stringBuilder.append("\n\nКРЕДИТ НА 12 МЕСЯЦА(20%) : Каждый месяц - > ").append(product.getPrice() * 1.5 / 12);
            }
            break;
            case "KRILL": {
                stringBuilder.append("\nНОМИ: ").append(product.getName());
                stringBuilder.append("\n\nНAРХИ: ").append(product.getPrice());
                stringBuilder.append("\n\n3-ОЙЛИК КРЕДИТ(20%) : Ҳар ойида - > ").append(product.getPrice() * 1.2 / 3);
                stringBuilder.append("\n\n6-ОЙЛИК КРЕДИТ(30%) : Ҳар ойида - > ").append(product.getPrice() * 1.3 / 6);
                stringBuilder.append("\n\n9-ОЙЛИК КРЕДИТ(40%) : Ҳар ойида - > ").append(product.getPrice() * 1.4 / 9);
                stringBuilder.append("\n\n12-ОЙЛИК КРЕДИТ(50%) : Ҳар ойида - > ").append(product.getPrice() * 1.5 / 12);
            }
            break;
        }

        var txt = stringBuilder.toString();
        sendMessage.setProtectContent(false);
        sendMessage.setText(txt);

        BotKeyboards.setInlineKeyboardButtonForCredit(sendMessage, product, language);
        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void sendMessageForOrderDescription(FurnitureBot furnitureBot, Message message, Order order) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setParseMode(ParseMode.HTML);
        sendMessage.setChatId(String.valueOf(message.getChatId()));
        var stringBuilder = new StringBuilder();

        stringBuilder.append("BUYURTMA:  ").append(order.getId());
        stringBuilder.append("\nNOMI:  ").append(order.getName());
        stringBuilder.append("\nVAQTI:  ").append(order.getOrderTime());
        stringBuilder.append("\nNARXI:  ").append(order.getTotalPrice());
        stringBuilder.append("\nXOLATI:  ").append(order.getOrderType());
        stringBuilder.append("\n\nMAXSULOTLAR:\n");

        var i = 1;
        for (Product product : order.getProducts()) {
            stringBuilder.append("\n").append(i).append(") ").append(product.getName()).append("\n\tNarxi: ").append(product.getPrice()).append("\n");
            i++;
        }

        if (order.getProducts().isEmpty()) {
            stringBuilder.append("No Product");
        }

        sendMessage.setText(stringBuilder.toString());
        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void setLanguage(FurnitureBot furnitureBot, String language) {
        switch (language) {
            case "Uzbek":
                furnitureBot.setLanguage(Language.UZBEK);

                break;
            case "Russian":
                furnitureBot.setLanguage(Language.RUSSIAN);

                break;
            case "English":
                furnitureBot.setLanguage(Language.ENGLISH);

                break;
            case "Krill":
                furnitureBot.setLanguage(Language.KRILL);
                break;
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
                BotService.sendMessage(furnitureBot, message, "Familiyangizni kiriting");
                furnitureBot.setSurname(true);
            }
            break;
            case "KRILL": {
                BotService.sendMessage(furnitureBot, message, "Фамилиянгизни киритинг");
                furnitureBot.setSurname(true);
            }
            break;
            case "ENGLISH": {
                BotService.sendMessage(furnitureBot, message, "Inser your surname");
                furnitureBot.setSurname(true);
            }
            break;
            case "RUSSIAN": {
                BotService.sendMessage(furnitureBot, message, "Введите вашу фамилию");
                furnitureBot.setSurname(true);
            }
            break;
            default:
                BotService.sendMessage(furnitureBot, message, "Familiyangizni kiriting");
                furnitureBot.setSurname(true);
                break;
        }
    }

    public static boolean checkUserOrCreate(FurnitureBot furnitureBot, User user, Message message) {
        var botController = furnitureBot.getBotController();
        var value = botController.doesUserExistByChatId(message.getChatId());
        if (value) {
            var user2 = botController.getUserByChatId(message.getChatId());
            if (user2.getName().equals(user.getName()) && user2.getSurname().equals(user.getSurname())) {
                user.setLanguage(furnitureBot.getLanguage());
                furnitureBot.setUser(user2);
                return true;

            } else return false;

        } else {
            user.setLanguage(furnitureBot.getLanguage());
            furnitureBot.setUser(botController.saveUser(user));
            return true;
        }
    }

    public static void orderProduct(FurnitureBot furnitureBot, Message message, User user, Language language, org.telegram.telegrambots.meta.api.objects.Location location, Product product) {
        var order = new Order();
        order.setOrderTime(LocalDate.now());
        order.setOrderType(OrderType.ACTIVE);
        order.setUserId(user.getId());

        if (product != null) {
            var list = new ArrayList<Product>();
            list.add(product);
            order.setProducts(list);
            order.setName(product.getName());
            order.setTotalPrice(product.getPrice());
        } else {
            var products = new ArrayList<>(user.getBasketProducts());
            order.setProducts(new ArrayList<>(products));
            order.setName(products.get(0).getName());
            order.setTotalPrice(BotService.calculateTotalPrice(user.getBasketProducts()));

        }

        Location location1 = new Location();
        location1.setLatitude(String.valueOf(location.getLatitude()));
        location1.setLongitude(String.valueOf(location.getLongitude()));

        order.setLocation(furnitureBot.getBotController().saveLocation(location1));

        var savedOrder = furnitureBot.getBotController().saveOrder(order);
        if (savedOrder != null) {
            user.addOrder(order);
            user.setBasketProducts(null);

            BotService.sendMessage(furnitureBot, message, "Buyutmsa berildi: ");
        }
    }

    private static Double calculateTotalPrice(Set<Product> products) {
        var price = 0d;

        for (Product product : products)
            price += product.getPrice();

        return price;
    }

    public static void shareLocation(FurnitureBot furnitureBot, Message message, Language language) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());

        var txt2 = "Lakatsiya yuborish";
        var txt = "Iltimos buyurtma berish uchun lokatsiyangizni yuboring ";

        switch (language.toString()) {
            case "ENGLISH": {
                txt2 = "Share location";
                txt = "Поделитесь адресом для заказа";
            }
            break;
            case "RUSSIAN": {
                txt2 = "Поделиться местоположением";
                txt = "Поделиться местоположением";
            }
            break;
            case "KRIL": {
                txt2 = "Лакация юбориш";
                txt = "Илтимос буюртма бериш учун локациянгизни юборинг";
            }
            break;
        }

        sendMessage.setText(txt);
        BotKeyboards.setShareContactKeyboardButton(sendMessage, txt2, true);
        furnitureBot.sendMessage(message, sendMessage);
    }

    public static void senMessageForPosition(FurnitureBot furnitureBot, Message message, Language language, int position) {
        var text0 = "Iltimos ishlaydigan tashkiltingizni  kiriting !";
        var text1 = "Iltimos Lavozimingizni kiriting !";
        var text2 = "Iltimos ish stajingiz kiriting !";
        var text3 = "Iltimos maoshingizni kiriting !";
        var text4 = "Iltimos paspert kopiyasini jo'nating !";
        var text5 = "Iltimos paspert orqa betining kopiyasini jo'nating !";
        var text6 = "Iltimos paspert bilan rasimga tushib  jo'nating !";
        var text7 = "Iltimos  telefon raqamingiz !";
        var text8 = "Iltimos ikkinchi telefon raqamingiz !";
        var text9 = "Iltimos plastik kartangizni kiriting!";

        switch (language.toString()) {
            case "ENGLISH": {

                text0 = "Please enter the organization you work for!";
                text1 = "Please enter your position !";
                text2 = "Please enter your work experience!";
                text3 = "Please enter your salary!";
                text4 = "Please send a copy of your passport!";
                text5 = "Please send a copy of the paspert back page!";
                text6 = "Please take a picture with a passport and send it!";
                text7 = "Your phone number, please!";
                text8 = "Your second phone number, please!";
                text9 = "Please enter your plastic card!";

            }
            break;
            case "RUSSIAN": {
                text0 = "Пожалуйста, укажите организацию, в которой вы работаете!";
                text1 = "Пожалуйста, введите вашу позицию!";
                text2 = "Пожалуйста, укажите свой опыт работы!";
                text3 = "Пожалуйста, введите вашу зарплату!";
                text4 = "Пожалуйста, пришлите копию паспорта!";
                text5 = "Пожалуйста, пришлите копию задней страницы паспорта!";
                text6 = "Пожалуйста, сфотографируйтесь с паспортом и пришлите!";
                text7 = "Ваш номер телефона, пожалуйста!";
                text8 = "Ваш второй номер телефона, пожалуйста!";
                text9 = "Пожалуйста, введите вашу пластиковую карту!";
            }
            break;
            case "KRILL": {
                text0 = "Илтимос ишлайдиган ташкилтингизни киритинг !";
                text1 = "Илтимос Лавозимингизни киритинг !";
                text2 = "Илтимос иш стажингиз киритинг !";
                text3 = "Илтимос маошингизни киритинг !";
                text4 = "Илтимос пасперт копиясини жўнатинг !";
                text5 = "Илтимос пасперт орқа бетининг копиясини жўнатинг !";
                text6 = "Илтимос пасперт билан расимга тушиб жўнатинг !";
                text7 = "Илтимос телефон рақамингиз !";
                text8 = "Илтимос иккинчи телефон рақамингиз !";
                text9 = "Илтимос пластик картангизни киритинг!";
            }
            break;
        }

        switch (position) {
            case 0: {
                BotService.sendMessage(furnitureBot, message, text0);
            }
            break;
            case 1: {
                BotService.sendMessage(furnitureBot, message, text1);
            }
            break;
            case 2: {
                BotService.sendMessage(furnitureBot, message, text2);
            }
            break;
            case 3: {
                BotService.sendMessage(furnitureBot, message, text3);
            }
            break;
            case 4: {
                BotService.sendMessage(furnitureBot, message, text4);
            }
            break;
            case 5: {
                BotService.sendMessage(furnitureBot, message, text5);
            }
            break;
            case 6: {
                BotService.sendMessage(furnitureBot, message, text6);
            }
            break;
            case 7: {
                BotService.sendMessage(furnitureBot, message, text7);
            }
            break;
            case 8: {
                BotService.sendMessage(furnitureBot, message, text8);
            }
            break;
            case 9: {
                BotService.sendMessage(furnitureBot, message, text9);
            }
            break;

        }
    }
}
