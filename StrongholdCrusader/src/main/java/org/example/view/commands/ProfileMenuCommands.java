package StrongholdCrusader.src.main.java.org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_USERNAME("profile change -u (?<username>.+)?"),
    CHANGE_NICKNAME("profile change -n (?<nickname>.+)?"),
    CHANGE_PASSWORD("profile change password -o (?<oldpassword>.+)? -n (?<newpassword>.+)?"),
    CHANGE_EMAIL("profile change -e (?<email>.+)?"),
    CHANGE_SLOGAN("profile change slogan -s (?<slogan>.+)?"),
    REMOVE_SLOGAN("profile remove slogan"),
    DISPLAY_HIGHSCORE("profile display highscore"),
    DISPLAY_RANK("profile display rank"),
    DISPLAY_SLOGAN("profile display slogan"),
    DISPLAY_PROFILE("profile display"),
    PASSWORD_NUMBER("^.{8,}$"),
    PASSWORD_SPACE("^\\S.+$"),
    PASSWORD_DIGITS(".*\\d.*"),
    PASSWORD_CAPITAL(".*[A-Z].*"),
    PASSWORD_SMALL(".*[a-z].*"),
    PASSWORD_OTHER(".*[^\\w\\s].*"),
    PASSWORD_TOTAL("^(?=.+[a-z]{1,})(?=.+[A-Z]{1,})(?=.+\\d{1,})(?=.+\\W{1,})[A-Za-z\\d\\W]{8,}$"),
    NICKNAME_Y("^\\S+$"),
    ///////////////////"NO SPACE"
    NICKNAME_N("^\"(?<main>.+)\"$"),
    EMAIL_FORMAT("[\\w\\.]+@[\\w\\.]+\\.[\\w\\.]+")
    ;
    private final String regex;

    ProfileMenuCommands(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}