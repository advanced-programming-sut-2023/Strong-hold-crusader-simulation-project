package view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
<<<<<<< HEAD
    ;
=======
    MOVE_MAP("map( up| down| right| left)+( (?<number>\\d+))?"),
    UP(".+up.*"),
    DOWN(".+down.*"),
    RIGHT(".+right.*"),
    LEFT(".+left.*");
>>>>>>> parent of 790ce96 (map menu complete)
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
