package uz.project.bot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.project.controllers.BotController;
import uz.project.models.Language;
import uz.project.utilds.BotService;


@Service
public class FurnitureBot extends TelegramLongPollingBot {
    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;

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
                    startBot(message);
                    System.out.println();
                } else if (text.equals("Kategory") || text.equals("Категория") || text.equals("Category") || text.equals("Категорйa")) {
                    BotService.sendMessage(this, message, text);

                } else if (text.equals("Buyurtmalar") || text.equals("Заказы") || text.equals("Orders") || text.equals("Буюртмалар")) {
                    BotService.sendMessage(this, message, text);

                } else if (text.equals("Savatcha") || text.equals("Корзина") || text.equals("Basket") || text.equals("Савадча")) {
                    BotService.sendMessage(this, message, text);

                } else if (text.equals("Profil") || text.equals("Профиль") || text.equals("Profile") || text.equals("Профил")) {
                    BotService.sendMessage(this, message, text);

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

    private void startBot(Message message) {
        BotService.sendMessageForLanguage(this, message,
                " Uzbek    ->   Iltimos tilni tanlang" +
                        "\nРусский  ->  Пожалуйста, выберите язык " +
                        "\nEnglish ->  Please choose Language " +
                        "\nКрилл    ->  Илтимос тилни танланг");
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
