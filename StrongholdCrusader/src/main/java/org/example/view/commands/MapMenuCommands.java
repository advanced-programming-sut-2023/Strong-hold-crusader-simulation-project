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
    SET_TEXTURE1("set texture -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\w+)"),
    SET_TEXTURE2("set texture -x1 (?<x1>-?\\d+) -y1 (?<y1>-?\\d+) -x2 (?<x2>-?\\d+) -y2 (?<y2>-?\\d+) -t (?<type>\\w+)"),
    CLEAR("clear -x (?<x>-?\\d+) -y (?<y>-?\\d+)"),
    DROP_ROCK("drop rock -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<dir>\\w+)"),
    DROP_TREE("drop tree -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\w+)"),
    DROP_UNIT("drop unit -x (?<x>-?\\d+) -y (?<y>-?\\d+) -t (?<type>\\w+) -c (?<count>\\d+)"),
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
