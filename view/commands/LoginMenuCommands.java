package view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    ;
    private final String regex;

    LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
