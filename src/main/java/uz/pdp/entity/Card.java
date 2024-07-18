package uz.pdp.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
public class Card {
    private final String id;
    @Setter
    private String number;
    @Setter
    private Double balance;

    public Card( String number) {
        this.number = number;
        this.balance = 0.0d;
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Card{" +
               "id='" + id + '\'' +
               ", number='" + number + '\'' +
               ", balance=" + balance +
               '}';
    }
}
