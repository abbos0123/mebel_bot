package uz.project.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import uz.project.models.Language;
import uz.project.models.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class BotServiceHelper {
    public static void setInlineKeyboardButtonForProductCategories(SendMessage sendMessage, Language language) {
        InlineKeyboardMarkup inlineKeyboardMarkup;

        List<List<InlineKeyboardButton>> inlineButtons = new ArrayList<>();

        for (ProductCategory productCategory : ProductCategory.values()) {

            switch (productCategory.toString()) {
                case "FURNITURE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Mebellar");
                            inlineKeyboardButton1.setCallbackData("FURNITURE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Мебель");
                            inlineKeyboardButton1.setCallbackData("FURNITURE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Furniture");
                            inlineKeyboardButton1.setCallbackData("FURNITURE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Мебеллар");
                            inlineKeyboardButton1.setCallbackData("FURNITURE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }break;

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
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Home appliance");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Маиший техникалар");
                            inlineKeyboardButton1.setCallbackData("HOME_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
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
                            inlineKeyboardButton1.setText("Телефоны, аксессуары, гаджеты");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Phones, accessories, gadgets");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Телефонлар, аксесуралар, гаджетлар");
                            inlineKeyboardButton1.setCallbackData("TELEPHONE_AND_ACCESSORY");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "LAPTOPS_AND_DESKTOPS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Noutbuk, PC, Printer");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Ноутбук, ПК, принтер");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Laptop, PC, Printer");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Ноутбук, ПC, Принтер");
                            inlineKeyboardButton1.setCallbackData("LAPTOPS_AND_DESKTOPS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "CARPETS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Gilamlar");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("ковры");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Carpets");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Гиламлар");
                            inlineKeyboardButton1.setCallbackData("CARPETS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "AUDIO_APPLIANCE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Televizor, Audio jihoz");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("ТВ, Аудиотехника");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Tv, Audio appliance");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Телевизор, Aудио жиҳоз");
                            inlineKeyboardButton1.setCallbackData("AUDIO_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "SPORTS_EQUIPMENTS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Sport anjomlari");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Спортивное оборудование");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Sports equipment");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Спорт анжомлари");
                            inlineKeyboardButton1.setCallbackData("SPORTS_EQUIPMENTS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "GIFTS_AND_SOUVENIRS": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Sovg'alar, suvinerlar");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Подарки, сувениры");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Gifts, souvenirs");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Совғалар, сувинерлар");
                            inlineKeyboardButton1.setCallbackData("GIFTS_AND_SOUVENIRS");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                    }
                }
                break;
                case "KITCHEN_APPLIANCE": {
                    switch (language.toString()){
                        case "UZBEK":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Oshxona jihozlari");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "RUSSIAN":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Кухонные приборы");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "ENGLISH":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Kitchen appliances");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
                        } break;
                        case "KRILL":{
                            List<InlineKeyboardButton> inlineKeyboardButtonList = new ArrayList<>();
                            InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
                            inlineKeyboardButton1.setText("Ошхона жиҳозлари");
                            inlineKeyboardButton1.setCallbackData("KITCHEN_APPLIANCE");
                            inlineKeyboardButtonList.add(inlineKeyboardButton1);
                            inlineButtons.add(inlineKeyboardButtonList);
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
