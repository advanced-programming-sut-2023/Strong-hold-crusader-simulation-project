package model;

import javafx.util.Pair;
import view.Menu;
import view.commands.ProfileMenuCommands;
import view.commands.ProfileMenuResponds;

import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;

public class User {
    private static final String[] securityQuestions = {
            "What is my father's name?",
            "What was my first pet's name?",
            "What is my mother's last name?"
    };
    private static User loggedInUser;
    private static boolean stayLoggedIn = false;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan = null;
    private int highScore;
    private int rank;
    private int recoveryQuestion;
    private String recoveryAnswer;
    public User() {

    }

    public static boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public String getEmail() {
        return email;
    }

    public int getRank() {
        return rank;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getNickname() {
        return nickname;
    }

    public static User findUserByUserName(String username){
        for (int i = 0 ; i < allUsers.size() ; i++){
            if (Objects.equals(allUsers.get(i).getUsername(), username)){
                return allUsers.get(i);
            }
        }
        return null;
    }
    public static String[] getSecurityQuestions(){
        return securityQuestions;
    }

    public int getRecoveryQuestion() {
        return recoveryQuestion;
    }

    public boolean isSecurityAnswerCorrect(String answer) {
        return recoveryAnswer.equals(answer);
    }

    public static User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }
    public static void stayLoggedIn(boolean stayLoggedIn) {
        User.stayLoggedIn = stayLoggedIn;
    }
    public static void readFile(){

    }
    public static void saveFile(){

    }
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return username;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public void removeSlogan() {
        this.slogan = null;
    }
    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }

    public static String checkUsernameFormat(String username) {
        if (username!=null){
            if (username.matches("[a-zA-Z0-9_]+")){
                if (User.findUserByUserName(username)==null){
                    return null;
                }
                else {
                    return "this username" + ProfileMenuResponds.ALREADY_EXITS.getText();
                }
            }
            else {
                return "username" + ProfileMenuResponds.INVALID_FORMAT.getText();
            }
        }
        else {
            return "username " + ProfileMenuResponds.EMPTY_FIELD.getText();
        }
    }

    public static String checkPasswordFormat(String newPassword) {


        if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_SPACE)==null){
            return "password" + ProfileMenuResponds.INVALID_FORMAT.getText();
        }
        else if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_NUMBER)==null){
            return ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 8 characters";
        }
        else if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_CAPITAL)==null){
            return ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 capital letter";
        }
        else if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_SMALL)==null){
            return ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 small letter";
        }
        else if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_DIGITS)==null){
            return ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 digit";
        }
        else if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_OTHER)==null){
            return ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 special character";
        }
        else if (User.getLoggedInUser().isPasswordCorrect(newPassword)){
            return ProfileMenuResponds.SAME_PASSWORD.getText();
        }
        else{
            return null;
        }
    }
    public static boolean isEmailDuplicate(String email){
        for (int i=0; i < allUsers.size();i++){
            if (Objects.equals(allUsers.get(i).getEmail(), email)){
                return true;
            }
        }
        return false;
    }
    public static String checkEmailFormat(String email) {
        if (email==null){
            return "email "+ProfileMenuResponds.EMPTY_FIELD.getText();
        }
        if (ProfileMenuCommands.getMatcher(email,ProfileMenuCommands.EMAIL_FORMAT)!=null){
            if (isEmailDuplicate(email)){
                return ProfileMenuResponds.DUPLICATE_EMAIL.getText();
            }
            else {
                return null;
            }
        }
        else {
            return "email "+ProfileMenuResponds.INVALID_FORMAT.getText();
        }
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
