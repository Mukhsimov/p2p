package uz.pdp.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import uz.pdp.entity.Card;
import uz.pdp.entity.User;
import uz.pdp.enums.State;

import java.util.List;

public class MessageService {
    private static MessageService messageService;
    MainBotService mainBotService = MainBotService.getInstance();
    UserService userService = UserService.getInstance();
    CardService cardService = CardService.getInstance();
    ReplyKeyboardMaker replyKeyboardMaker = new ReplyKeyboardMaker();

    public static MessageService getInstance() {
        if (messageService == null) return new MessageService();
        return messageService;
    }


    public void service(Update update) {
        SendMessage sendMessage = new SendMessage();
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();
        sendMessage.setChatId(chatId);
        Message message = update.getMessage();


         User user = userService.get(chatId);

        if (user == null) {

            String name = update.getMessage().getChat().getFirstName();

            user = new User(name, chatId);
            userService.create(user);
        }


        if (stateController(update, sendMessage, chatId, user)) {
            return;
        }

        switch (text) {
            case "/start" -> {
                sendMessage.setText("welcome");
                ReplyKeyboardMarkup menu = markupMenu();
                sendMessage.setReplyMarkup(menu);
            }
            case "Add card" -> {
                sendMessage.setText("add card: enter number");
                user.setState(State.ADD_CARD);
                userService.update(user);
            }
            case "Show cards" -> {
                List<Card> cards = user.getCards();
                StringBuilder mssg = new StringBuilder("Cardlar haqida malumot:\n");

                for (Card card : cards) {
                    mssg.append(card);
                    mssg.append("\n");
                }
                sendMessage.setText(mssg.toString());


            }
            case "Transaction" -> {

            }
            case "Deposit" -> {
                sendMessage.setText("Deposit");
                user.setState(State.DEPOSIT);
                userService.update(user);

            }
            case "Delete card" -> {

            }

            default -> throw new IllegalStateException("Unexpected value: " + text);
        }
        mainBotService.executeMessage(sendMessage);


    }

    public  ReplyKeyboardMarkup markupMenu() {
        return replyKeyboardMaker.replyMaker(List.of("Add card", "Show cards", "Transaction", "Deposit", "Delete card"));
    }

    private boolean stateController(Update update, SendMessage sendMessage, Long chatId, User user) {
        boolean bool = false;
        State state = user.getState();
        switch (state) {
            case ADD_CARD -> {
                bool = true;
                String cardNumber = update.getMessage().getText();
                if (userService.isValidCardNumber(cardNumber)) {
                    Card card = new Card(cardNumber);
                    user.addCard(card);
                    sendMessage.setText("card is added");
                    sendMessage.setReplyMarkup(markupMenu());
                } else {
                    sendMessage.setText("incorrect card number entered");
                }


            }
            case DEPOSIT -> {



            }


        }

        user.setState(State.DEF);
        userService.update(user);
        if (bool) mainBotService.executeMessage(sendMessage);
        return bool;
    }


}
