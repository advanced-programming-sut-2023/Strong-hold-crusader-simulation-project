package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    MOVE_MAP("map( up| down| right| left)+( (?<number>\\d+))?"),
    UP(".+up.*"),
    DOWN(".+down.*"),
    RIGHT(".+right.*"),
    LEFT(".+left.*"),
    SHOW_DETAILS("show details (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    MAP_X(".* -x (?<mapX>\\d+).*"),
    MAP_Y(".* -y (?<mapY>\\d+).*");
    private final String regex;
    MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
