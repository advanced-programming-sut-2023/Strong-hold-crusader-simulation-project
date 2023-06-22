package org.example.view.Responds;

public enum ProfileMenuResponds {
    INVALID("Invalid Command!"),
    EMPTY_FIELD("field is empty"),
    INVALID_FORMAT("format is invalid"),
    ALREADY_EXITS("already exits"),
    CHANGED(" changed successfully"),
    WEAK_PASSWORD("your new password is weak!: "),
    INCORRECT_PASSWORD("Current password is incorrect!"),
    SAME("Please enter a new "),
    ERROR_CONFIRM("error in confirm"),
    DUPLICATE_EMAIL("this email address already exits"),
    EMPTY_SLOGAN("slogan is empty!"),
    ;

    private final String text;

    public String getText() {
        return text;
    }

    ProfileMenuResponds(String regex) {
        this.text = regex;
    }

}