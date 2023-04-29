package org.example.view.commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GovernmentMenuResponds {
    SET(" successfully set"),
    INVALID("invalid number for ");
    private final String text;
    GovernmentMenuResponds(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }
}
