package org.example.controller;

import org.example.model.Government;
import org.example.model.Resources;
import org.example.model.Trade;
import org.example.model.User;

import java.util.regex.Matcher;

public class TradeMenuController extends Controller {
    public static String tradeNotification() {
        String trades = "";
        for (Trade trade : currentGame.getCurrentTurn().getTradeNotification()) {
            trades += "\n" + "trade id: " + trade.getId() + "\n" +
                    "sender: " + trade.getRequester().getOwner().getUsername() + "\n" +
                    "resource: " + trade.getResource().getName() + "\n" +
                    "amount: " + trade.getAmount() + "\n" +
                    "message: " + trade.getRequestMessage() + "\n";
        }
        currentGame.getCurrentTurn().getTradeNotification().clear();
        return trades;
    }

    public static String tradeList(){
        String trades = "";
        for (Trade trade : Trade.getAllTrades()) {
            if (trade.isAccepted() || !trade.getRecipient().equals(currentGame.getCurrentTurn()))
                continue;
            trades += "\n" + "trade id: " + trade.getId() + "\n" +
                    "sender: " + trade.getRequester().getOwner().getUsername() + "\n" +
                    "resource: " + trade.getResource().getName() + "\n" +
                    "amount: " + trade.getAmount() + "\n" +
                    "message: " + trade.getRequestMessage() + "\n";
        }
        return trades;
    }
    public static String tradeHistory(){
        String trades = "";
        for (Trade trade : Trade.getAllTrades()) {
            if (!trade.isAccepted() || !trade.getRecipient().equals(currentGame.getCurrentTurn()))
                continue;
            trades += "\n" + "trade id: " + trade.getId() + "\n" +
                    "sender: " + trade.getRequester().getOwner().getUsername() + "\n" +
                    "resource: " + trade.getResource().getName() + "\n" +
                    "amount: " + trade.getAmount() + "\n" +
                    "request message: " + trade.getRequestMessage() + "\n" +
                    "accept message: " + trade.getAcceptMessage() + "\n";
        }
        return trades;
    }

    public static String trade(Matcher matcher) {
        String stringResource = matcher.group("resource");
        int amount = Integer.parseInt(matcher.group("amount"));
        int price = Integer.parseInt(matcher.group("price"));
        String message = matcher.group("message");
        String username = matcher.group("username");
        Resources resource;
        if ((resource = Resources.getResourceByName(stringResource)) == null)
            return "enter a valid resource!";
        User user;
        if ((user = User.getUserByUsername(username)) == null)
            return "this username doesn't exist!";
        Government government;
        if ((government = Government.getGovernmentByUser(user)) == null)
            return "You're not playing with this user right now!";
        if (government.getResourceCount().get(resource) < amount)
            return "this user doesn't have this amount of " + resource.getName();
        if (currentGame.getCurrentTurn().getBalance() < price)
            return "You don't have enough money for this trade";
        government.getTradeNotification().add(new Trade(government, currentGame.getCurrentTurn(),
                price, resource, amount, message));
        return "Trade request successfully sent";
    }

    public static String tradeAccept(Matcher matcher) {
        String message = matcher.group("message");
        Trade trade = Trade.getTradeById(Integer.parseInt(matcher.group("id")));
        if (trade == null)
            return "no trade with this id!";
        Government recipient = currentGame.getCurrentTurn();
        if (!trade.getRecipient().equals(recipient))
            return "this trade is not sent to you";
        if (trade.isAccepted())
            return "this trade is already accepted";
        if (trade.getAmount() > recipient.getResourceCount().get(trade.getResource()))
            return "you don't have enough of this item";
        Government requester = trade.getRequester();
        if (requester.getBalance() < trade.getPrice())
            return "the other guy doesn't have enough money";
        trade.setAccepted(true);
        trade.setAcceptMessage(message);
        recipient.getResourceCount().replace(trade.getResource(),
                recipient.getResourceCount().get(trade.getResource()) - trade.getAmount());
        requester.getResourceCount().replace(trade.getResource(),
                requester.getResourceCount().get(trade.getResource()) + trade.getAmount());
        recipient.changeBalance(trade.getPrice());
        requester.changeBalance(-trade.getPrice());
        return "You have successfully accepted this trade";
    }
}
