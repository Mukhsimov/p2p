package uz.pdp.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public class CallbackService {

    public void service(Update update) {

    }




















    private static CallbackService callbackService;
    public static CallbackService getInstance() {
        if (callbackService == null) return new CallbackService();
        return callbackService;
    }
}
