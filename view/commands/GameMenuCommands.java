package view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SHOW_MAP("show map (-x \\d+ -y \\d+|-y \\d+ -x \\d+"),
    MAP_X(".* -x (?<mapX>\\d+).*"),
    MAP_Y(".* -y (?<mapY>\\d+).*")
    ;
    private final String regex;

    GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
