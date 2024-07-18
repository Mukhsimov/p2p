package uz.pdp;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.pdp.service.MainBotService;

import java.util.List;

public class Main {
    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi register = new TelegramBotsApi(DefaultBotSession.class);
        register.registerBot(MainBotService.getInstance());

    }
}