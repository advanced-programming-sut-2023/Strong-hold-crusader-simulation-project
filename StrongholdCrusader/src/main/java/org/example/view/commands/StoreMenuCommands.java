package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StoreMenuCommands {
    SHOW_PRICE_LIST("show price list"),
    BUY("buy (-i \\w+( \\w+)? -a \\d+|-a \\d+ -i \\w+( \\w+)?)"),
    SELL("sell (-i \\w+( \\w+)? -a \\d+|-a \\d+ -i \\w+( \\w+)?)"),
    ITEM(".* -i (?<item>\\w+( \\w+)?).*"),
    AMOUNT(".* -a (?<amount>\\d+).*")
    ;
    private final String regex;

    StoreMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StoreMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
