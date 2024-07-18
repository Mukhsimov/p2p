package uz.pdp.service;

import uz.pdp.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class UserService implements BaseService<User> {
    private final WriterAndLoader<User> writerAndLoader = new WriterAndLoader<>("src/main/resources/User.json");

    @Override
    public User get(String id) {
        List<User> list = writerAndLoader.fileLoader(User.class);
        for (User user : list) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean update(User user) {

        List<User> list = writerAndLoader.fileLoader(User.class);
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(user.getId())) {
                list.set(i, user);
                writerAndLoader.fileWrite(list);
                return true;
            }
        }
        return false;
    }

    @Override
    public void create(User user) {
        List<User> list = writerAndLoader.fileLoader(User.class);
        if (list == null ) list = new ArrayList<>();
        list.add(user);
        writerAndLoader.fileWrite(list);
    }

    @Override
    public boolean delete(String id) {
        List<User> list = writerAndLoader.fileLoader(User.class);
        boolean b = list.removeIf((user -> user.getId().equals(id)));
        if (b) {
            writerAndLoader.fileWrite(list);
            return true;
        } else return false;

    }

    @Override
    public List<User> getListByFilter(Predicate<User> filter) {
        List<User> users = writerAndLoader.fileLoader(User.class);
        if (users == null || users.isEmpty()) {
            return new ArrayList<>();
        } else return users.stream()
                .filter(filter)
                .toList();
    }


    private static UserService userService;
    public static UserService getInstance() {
        if (userService == null) return new UserService();
        return userService;
    }


    public User get(Long chatId) {
        List<User> users = writerAndLoader.fileLoader(User.class);
        if (users == null || users.isEmpty()) return null;
        for (User user : users) {
            if (user.getChatId().equals(chatId)) return user;
        }
        return null;
    }

    public boolean isValidCardNumber(String cardNumber) {


        if (cardNumber.length() != 16) {
            return false;
        }


        for (int i = 0; i < cardNumber.length(); i++) {
            if (!Character.isDigit(cardNumber.charAt(i))) {
                return false;
            }
        }

        return true;
    }
}
