package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    SIGNUP("signup"),
    LOGIN("user login (-u|-p|\\S|\".+\"| )+"),
    STAY_LOGGED_IN(LOGIN.regex + " --stay-logged-in"),
    USERNAME(".+ -u (?<username>([^\\-\\s]\\S+|\".+\")).*"),
    PASSWORD(".+ -p (?<password>([^\\-\\s]\\S+|\".+\")).*"),
    FORGET_PASS("forgot my password" + USERNAME.regex.substring(2,36));
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
