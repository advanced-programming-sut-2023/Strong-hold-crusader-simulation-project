package model;

import java.util.HashMap;

public class Trade {
    private int id;
    private Government recipient;
    private Government requester;
    private int price;
    private HashMap<Recourses, Integer> recourse;
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

    public HashMap<Recourses, Integer> getRecourse() {
        return recourse;
    }

    public String getMessage() {
        return message;
    }
}
