package uz.pdp.service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.enums.Constants;

public class MainBotService extends TelegramLongPollingBot {

    static CallbackService callbackService = CallbackService.getInstance();
    static MessageService messageService = MessageService.getInstance();

    @Override
    public void onUpdateReceived(Update update) {
        SendMessage sendMessage = new SendMessage();
        if (update.hasCallbackQuery()) {
            callbackService.service(update);
        } else {
            messageService.service(update);
        }


    }


    private static MainBotService mainBotService;

    public static MainBotService getInstance() {
        if (mainBotService == null) {
            mainBotService = new MainBotService();
        }
        return mainBotService;
    }


    @Override
    public String getBotToken() {
        return Constants.BOT_TOKEN;
    }

    @Override
    public String getBotUsername() {
        return Constants.BOT_USERNAME;
    }

    public void executeMessage(SendMessage sendMessage) {

        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {

        }

    }
}
