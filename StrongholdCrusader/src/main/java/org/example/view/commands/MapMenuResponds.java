package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuResponds {
    INVALID("invalid command"),
    TEXTURE_CHANGED("texture changed"),
    INVALID_POSITION("invalid position!"),
    CLEAR("is clear"),
    CANT_BE_CHANGED("texture can't be changed, there is a "),
    CANT_BE_CLEARED("texture can't be cleared, there is a "),
    SET_TREE("The tree is placed"),
    SET_Rock("The rock is placed"),
    SET_UNIT("unit(s) placed"),
    DONE("done"),
    SET_ERROR("The type of texture is not suitable for the "),
    ERROR("there is something here!!"),
    OUT_OF_NUMBER("out of number"),
    BAD_TEXTURE_UNIT("The texture is not suitable for the unit ...will sink");
    private final String regex;
    MapMenuResponds(String regex) {
        this.regex = regex;
    }
    public static Matcher getMatcher(String input, org.example.view.commands.MapMenuResponds command) {
        Matcher matcher = Pattern.compile(command.regex).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    public String getRegex() {
        return regex;
    }

}
