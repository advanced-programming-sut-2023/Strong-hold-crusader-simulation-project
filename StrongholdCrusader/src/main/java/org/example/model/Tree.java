package org.example.model;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Tree {
    DESERT("desert"),
    CHERRY("cherry"),
    OLIVE("olive"),
    COCONUT("coconut"),
    DATES("dates");
    private final String type;
    Tree(String type){
        this.type=type;
    }
    public static Matcher getMatcher(String input, Tree command) {
        Matcher matcher = Pattern.compile(command.type).matcher(input);
        if(matcher.matches()) return matcher;
        else return null;
    }
    public static Tree findType(String string){
        for (Tree tree: Tree.values()){
            if (Objects.equals(tree.getType(), string)){
                return tree;
            }
        }
        return null;
    }
    public String getType() {
        return type;
    }
}
