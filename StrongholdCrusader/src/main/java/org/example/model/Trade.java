package StrongholdCrusader.src.main.java.org.example.model;

import java.util.HashMap;

public class Trade {
    private int id;
    private Government recipient;
    private Government requester;
    private int price;
    private HashMap<Resources, Integer> resource;
    private String message;

    public int getId() {
        return id;
    }

    public Government getRecipient() {
        return recipient;
    }

    public Government getRequester() {
        return requester;
    }

    public int getPrice() {
        return price;
    }

    public HashMap<Resources, Integer> getResource() {
        return resource;
    }

    public String getMessage() {
        return message;
    }
}
