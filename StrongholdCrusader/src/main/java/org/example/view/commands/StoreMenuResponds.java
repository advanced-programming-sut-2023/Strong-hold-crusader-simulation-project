package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuResponds {
    NOT_ENOUGH_BALANCE("not enough balance"),
    BOUGHT("you bought "),
    SOLD("you sold "),
    INVALID("invalid command for");

    StoreMenuResponds(String text) {
        this.text = text;
    }

    public static Matcher getMatcher(String input, StoreMenuResponds command){
        Matcher matcher = Pattern.compile(command.getText()).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    private final String text;
    public String getText() {
        return text;
    }
}
