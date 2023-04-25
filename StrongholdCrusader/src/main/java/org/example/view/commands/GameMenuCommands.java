package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    SHOW_MAP("show map (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    MAP_X(".* -x (?<mapX>\\d+).*"),
    MAP_Y(".* -y (?<mapY>\\d+).*"),
    DROP_BUILDING("drop building( -x| -y| -type| \\d+| \\w+)+"),
    SELECT_BUILDING("select building (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    SELECT_UNIT("select unit (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    SET_TEXTURE("set texture.+"),
    CLEAR("clear (-x \\d+ -y \\d+|-y \\d+ -x \\d+)"),
    DROP_ROCK("drop rock( -x| -y| -direction| \\d+| \\w)+"),
    DROP_TREE("drop tree( -x| -y| -type| \\d+| \\w+)+")
    //TODO not complete, not complete at all
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
