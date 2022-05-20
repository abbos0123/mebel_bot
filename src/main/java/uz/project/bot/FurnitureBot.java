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
import uz.project.services.StudentService;
import uz.project.utilds.BotService;


@Service
public class FurnitureBot extends TelegramLongPollingBot {

    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;

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
                }

            } else if (message.hasContact()) {
                var contact = message.getContact();
                BotService.sendMessage(this, message, contact.toString());
            }

        }else if (update.hasCallbackQuery()){
            Message message = update.getCallbackQuery().getMessage();
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String data = callbackQuery.getData();

            if (data.equals("Uzbek") || data.equals("Russian") || data.equals("English") || data.equals("Krill")) {
                BotService.setLanguage(this, data);
                BotService.showChosenLanguage(this, message, data);
            }

            if (data.equals("share_contact")){
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


    public  void sendMessage(Message message, SendMessage sendMessage){
        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}
