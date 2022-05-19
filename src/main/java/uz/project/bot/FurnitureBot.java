package uz.project.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.project.models.Language;
import uz.project.utilds.BotService;


@Service
public class FurnitureBot extends TelegramLongPollingBot {

    private Language language = Language.UZBEK;
    private Long currentChatId = -1L;


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

                if (text.equals("Uzbek") || text.equals("Russian") || text.equals("English") || text.equals("Krill")) {
                    BotService.setLanguage(this, text);
                }

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
        sendMessage(message,
                " Uzbek    ->   Iltimos tilni tanlan " +
                        "\nРусский  ->  Пожалуйста, выберите язык " +
                        "\nEnglish ->  Please choose Language " +
                        "\nКрилл    ->  Илтимос тилни танланг)", true);
    }

    private void sendMessage(Message message, String text, Boolean openKey) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(message.getChatId().toString());

        if (openKey)
        BotService.setLanguageKeyboardButton(sendMessage);

        try {
            execute(sendMessage);
            System.out.println(message.getChatId().toString());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

}
