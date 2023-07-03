package org.example.view.commands;

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
