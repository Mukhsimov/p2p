package uz.pdp.entity;



import lombok.Getter;
import lombok.Setter;
import uz.pdp.enums.State;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter

public class User {
    private final String id;
    @Setter
    private String name;
    private final Long chatId;
    @Setter
    private State state;
    @Setter
    private List<Card> cards;

    public User(String name, Long chatId) {
        this.cards = new ArrayList<>();
        this.name = name;
        this.id = UUID.randomUUID().toString();
        this.chatId = chatId;
        this.state = State.DEF;
    }

    @Override
    public String toString() {
        return "User{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
    public void addCard(Card card){
        cards.add(card);
    }
}
