package model;

import javafx.util.Pair;

import java.util.ArrayList;

public class User {
    private static User loggedInUser;
    private static boolean stayLoggedIn;
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan = null;
    private Pair<Integer, String> recoveryQA;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
        return null;
    }

    public static String checkPasswordFormat(String password) {
        return null;
    }

    public static String checkEmailFormat(String email) {
        return null;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }
}
