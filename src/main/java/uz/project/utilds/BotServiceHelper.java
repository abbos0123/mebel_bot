package uz.project.utilds;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.project.models.Language;
import uz.project.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class BotServiceHelper {
    public static void setInlineKeyboardButtonForProductCategories(SendMessage sendMessage, Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();

        for (ProductCategory productCategory : ProductCategory.values()) {

            switch (productCategory.toString()) {
                case "HOME_APPLIANCE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "TELEPHONE_AND_ACCESSORY": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Telefonlar, aksesuralar, gadjetlar");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "LAPTOPS_AND_DESKTOPS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "CARPETS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "AUDIO_APPLIANCE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "SPORTS_EQUIPMENTS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "GIFTS_AND_SOUVENIRS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
                case "KITCHEN_APPLIANCE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Maishiy texnikalar");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Бытовая техника");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                        } break;
                    }
                }
                break;
            }

        }

        inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(inlineButtons);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }
}
