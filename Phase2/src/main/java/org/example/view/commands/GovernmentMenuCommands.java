package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernmentMenuCommands {
    SHOW_POP_FACTORS("show popularity factors"),
    SHOW_POP("show popularity"),
    SHOW_FOOD_LIST("show food list"),
    FOOD_RATE("food rate -r (?<rateNumber>-?\\d+)"),
    FOOD_RATE_SHOW("food rate show"),
    TAX_RATE("tax rate -r (?<rateNumber>-?\\d+)"),
    TAX_RATE_SHOW("tax rate show"),
    FEAR_RATE("fear rate -r (?<rateNumber>-?\\d+)"),
    ;
    private final String regex;

    GovernmentMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GovernmentMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
