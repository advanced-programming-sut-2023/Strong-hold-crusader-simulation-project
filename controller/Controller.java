package controller;

import model.User;

public class Controller {
    public static String captcha() {
        return null;
    }
    public static String doubleQuoteRemover(String string) {
        if (string.charAt(0) == '\"')
            return string.substring(1,string.length() - 1);
        return string;
    }
}
