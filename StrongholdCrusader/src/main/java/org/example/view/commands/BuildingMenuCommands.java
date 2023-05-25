package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum BuildingMenuCommands {
    CREATE_UNIT("create unit -t (?<type>\\w+( \\w+)?) -c (?<count>\\d+)");
    private final String regex;

    BuildingMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, BuildingMenuCommands command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
}
