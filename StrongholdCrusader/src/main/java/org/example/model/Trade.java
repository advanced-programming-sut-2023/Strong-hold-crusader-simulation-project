package org.example.model;

import java.util.ArrayList;

public class Trade {
    private static final ArrayList<Trade> allTrades = new ArrayList();
    private int id;
    private Government recipient;
    private Government requester;
    private int price;
    private Resources resource;
    private int amount;
    private String requestMessage;
    private String acceptMessage;
    private boolean accepted;

    public Trade(Government recipient, Government requester,
                 int price, Resources resource, int amount, String requestMessage){
        this.id = allTrades.size() + 1;
        this.recipient = recipient;
        this.requester = requester;
        this.price = price;
        this.resource = resource;
        this.amount = amount;
        this.requestMessage = requestMessage;
        this.acceptMessage = null;
        this.accepted = false;
        allTrades.add(this);
    }

    public static Trade getTradeById(int id) {
        for (Trade trade : allTrades) {
            if (trade.id == id)
                return trade;
        }
        return null;
    }

    public String getAcceptMessage() {
        return acceptMessage;
    }

    public void setAcceptMessage(String acceptMessage) {
        this.acceptMessage = acceptMessage;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public static ArrayList<Trade> getAllTrades() {
        return allTrades;
    }
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

    public Resources getResource() {
        return resource;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRecipient(Government recipient) {
        this.recipient = recipient;
    }

    public void setRequester(Government requester) {
        this.requester = requester;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setResource(Resources resource) {
        this.resource = resource;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public String getRequestMessage() {
        return requestMessage;
    }
}
