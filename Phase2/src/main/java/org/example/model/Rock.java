package org.example.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Rock {
    R("r"),
    S("s"),
    E("e"),
    N("n"),
    W("w");;
    private final String direction;
    Rock(String direction) {
        this.direction = direction;
    }
    public static Matcher getMatcher(String input, Rock command) {
        Matcher matcher = Pattern.compile(command.direction).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    public static Rock findDir(String string){
        for (Rock rock: Rock.values()){
            if (Objects.equals(rock.getDirection(), string)){
                return rock;
            }
        }
        return null;
    }
    public String getDirection() {
        return direction;
    }
}
