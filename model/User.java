package model;

import javafx.util.Pair;

import java.util.ArrayList;

public class User {
    private static final ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan = null;
    private Pair<Integer, String> recoveryQA;
    public User() {

    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
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

    public static String checkUsernameFormat(String username) {
        return null;
    }

    public static String checkPasswordFormat(String password) {
        return null;
    }

    public static String checkEmailFormat(String email) {
        return null;
    }
}
