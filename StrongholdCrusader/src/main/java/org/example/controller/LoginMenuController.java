package StrongholdCrusader.src.main.java.org.example.controller;

import StrongholdCrusader.src.main.java.org.example.model.User;
import StrongholdCrusader.src.main.java.org.example.view.commands.LoginMenuCommands;

import java.util.regex.Matcher;

public class LoginMenuController extends Controller {
    private static int wrongPasswordCount = 0;
    private static long lastLoginAttempt = 0;
    public static String loginUser(String input) {
        Matcher usernameMatcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.USERNAME);
        Matcher passwordMatcher = LoginMenuCommands.getMatcher(input, LoginMenuCommands.PASSWORD);
        if(usernameMatcher == null || passwordMatcher == null)
            return "invalid command";
        String username = doubleQuoteRemover(usernameMatcher.group("username"));
        String password = doubleQuoteRemover(passwordMatcher.group("password"));
        boolean stayLoggedIn = LoginMenuCommands.getMatcher(input, LoginMenuCommands.STAY_LOGGED_IN) != null;
        if(System.currentTimeMillis() < lastLoginAttempt + wrongPasswordCount * 5000L)
            return "You have to wait " + (5 * wrongPasswordCount) + " seconds before next attempt";
        User user;
        if((user = User.getUserByUsername(username)) == null)
            return "Username and password didn't match";
        if(!user.isPasswordCorrect(password)) {
            lastLoginAttempt = System.currentTimeMillis();
            wrongPasswordCount++;
            return "Username and password didn't match";
        }
        wrongPasswordCount = 0;
        lastLoginAttempt = 0;
        User.setLoggedInUser(user);
        User.stayLoggedIn(stayLoggedIn);
        return "user logged in successfully!";
    }
}
