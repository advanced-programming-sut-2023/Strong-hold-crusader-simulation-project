package controller;

import model.User;
import view.commands.ProfileMenuCommands;
import view.commands.ProfileMenuResponds;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenuController extends Controller {
    public static String changeUsername(Matcher matcher) {
        String check=User.checkUsernameFormat(matcher.group("username"));
        if (check==null){
            User.getLoggedInUser().setUsername(matcher.group("username"));
            return "username" + ProfileMenuResponds.CHANGED.getText();
        }
        else {
            return check;
        }
    }
    public static String changePassword(Matcher matcher, Scanner scanner) {
        if (!User.getLoggedInUser().isPasswordCorrect(matcher.group("oldpassword"))){
            return ProfileMenuResponds.INCORRECT_PASSWORD.getText();
        }
        if (matcher.group("newpassword")==null){
            return "new password" + ProfileMenuResponds.EMPTY_FIELD;
        }
        if (matcher.group("oldpassword")==null){
            return "old password " + ProfileMenuResponds.EMPTY_FIELD;
        }
        String check=User.checkPasswordFormat(matcher.group("newpassword"));
        if (check==null){
            String confirm = scanner.nextLine();
            if (Objects.equals(confirm, matcher.group("newpassword"))){
                User.getLoggedInUser().setPassword(matcher.group("newpassword"));
                return "password " + ProfileMenuResponds.CHANGED.getText();
            }
            else {
                return ProfileMenuResponds.ERROR_CONFIRM.getText();
            }
        }
        else {
            return check;
        }
    }
    public static String changeNickname(Matcher matcher) {
        if (matcher.group("nickname")==null){
            return "nickname "+ProfileMenuResponds.EMPTY_FIELD.getText();
        }
        if ((ProfileMenuCommands.getMatcher(matcher.group("nickname"),ProfileMenuCommands.NICKNAME_N)!=null)||
                (ProfileMenuCommands.getMatcher(matcher.group("nickname"),ProfileMenuCommands.NICKNAME_Y)!=null)){
            User.getLoggedInUser().setNickname(matcher.group("nickname"));
            return "nickname" + ProfileMenuResponds.CHANGED.getText();
        }
        else {
            return "nickname" + ProfileMenuResponds.INVALID_FORMAT.getText();
        }
    }
    public static String changeEmail(Matcher matcher) {
        String check=User.checkEmailFormat(matcher.group("email").toLowerCase());
        if (check!=null){
            return check;
        }
        else {
            User.getLoggedInUser().setEmail(matcher.group("email").toLowerCase());
            return "email "+ProfileMenuResponds.CHANGED;
        }
    }
    public static String changeSlogan(Matcher matcher) {
        if (Objects.equals(matcher.group("slogan"), User.getLoggedInUser().getSlogan())){
            return ProfileMenuResponds.SAME_SLOGAN.getText();
        }
        User.getLoggedInUser().setSlogan(matcher.group("slogan"));
        return "slogan "+ ProfileMenuResponds.CHANGED.getText();
    }
    public static String removeSlogan(Matcher matcher) {
        if (Objects.equals(null, User.getLoggedInUser().getSlogan())){
            return ProfileMenuResponds.EMPTY_SLOGAN.getText();
        }
        User.getLoggedInUser().setSlogan(null);
        return "slogan "+ ProfileMenuResponds.CHANGED.getText()+" (is empty)";
    }
    public static String displayHighScore() {
        return Integer.toString(User.getLoggedInUser().getHighScore());
    }
    public static String displayRank() {
        return Integer.toString(User.getLoggedInUser().getRank());
    }
    public static String displaySlogan() {
        if (User.getLoggedInUser().getSlogan()==null){
            return ProfileMenuResponds.EMPTY_SLOGAN.getText();
        }
        return "slogan: "+ User.getLoggedInUser().getSlogan();
    }
    public static String displayAll() {
        return "*Info*" +
                "\nuser name: "+User.getLoggedInUser().getUsername()+
                "\nnick name: "+User.getLoggedInUser().getNickname()+
                "\nhigh score: "+ Integer.toString(User.getLoggedInUser().getHighScore())+
                "\nrank: "+ Integer.toString(User.getLoggedInUser().getRank())+
                "\nslogan: "+User.getLoggedInUser().getSlogan();
    }
}