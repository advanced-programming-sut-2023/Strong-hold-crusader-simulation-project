package model;

import javafx.util.Pair;
import model.InputOut.Regex;
import model.InputOut.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class User implements Serializable{
    private static final String[] securityQuestions = {
            "What is my father's name?",
            "What was my first pet's name?",
            "What is my mother's last name?"
    };
    private static User loggedInUser;
    private static boolean stayLoggedIn = false;
    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan = null;
    private int recoveryQuestion;
    private String recoveryAnswer;
    public boolean isSecurityAnswerCorrect(String answer) {
        return recoveryAnswer.equals(answer);
    }
    public static void stayLoggedIn(boolean stayLoggedIn) {
        User.stayLoggedIn = stayLoggedIn;
    }

    public static void readFile(){
        try{
            File f = new File("userData.ser");
            if(!f.exists() || f.isDirectory()) {
                saveFile();
            }
            FileInputStream readData = new FileInputStream("userData.ser");
            ObjectInputStream readStream = new ObjectInputStream(readData);

            allUsers = (ArrayList<User>) readStream.readObject();
            readStream.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(){
        try{
            FileOutputStream writeData = new FileOutputStream("userData.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);

            writeStream.writeObject(allUsers);
            writeStream.flush();
            writeStream.close();

        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        String result = "Users :\n";
        result += ("name :" + this.getUsername() +"\n");
        result += ("password :" + this.getPassword() +"\n");
        result += ("nickname :" + this.getNickname() +"\n");
        result += ("slogan :" + this.getSlogan() + "\n");
        result += ("email :" + this.getEmail() + "\n");
        return result;
    }

    public void removeSlogan() {
        this.slogan = null;
    }
    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public String getUsername() {
        return username;
    }
    public boolean isPasswordCorrect(String password) {
        return password.equals(this.password);
    }
    public User(String  username , String password , String email , String nickname , String slogan) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.slogan = slogan;
        allUsers.add(this);
    }
    public static String UsernameCheck(String username , Scanner scanner){
        String newUsername;
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            if (User.getAllUsers().get(i).getUsername().equals(username)){
                System.out.println(Response.usernameAlreadyExist.getResponse());
                newUsername = randomUsername(username);
                System.out.println("your new username is :" + newUsername);
                System.out.println("if you agree enter an \"y\" to continue else enter any character to exist.");
                if (!scanner.nextLine().equals("y")){
                    return Response.youAreInSignupMenu.getResponse();
                }
                username = newUsername;
            }
        }
        return username;
    }
    private static String randomUsername(String old){
        Random random = new Random();
        String newUsername = old;
        newUsername += ((random.nextInt() % 1000) + 1000);
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            if (User.getAllUsers().get(i).getUsername().equals(newUsername)){
                return randomUsername(old);
            }
        }
        return newUsername;
    }
    public static String checkPassword(String password , String confirm){
        if (!password.equals(confirm)){
            return Response.passwordDifferentWithConfirm.getResponse();
        }
        String error = "password errors :\n";
        if (Regex.password.getMatcher(password) != null){
            return null;
        }
        else if (Regex.passwordErrorNumber.getMatcher(password) == null){
            error += Response.noNumberPassword.getResponse() + "\n";
        }
        else if (Regex.passwordInvalidLength.getMatcher(password) != null){
            error += Response.inValidLengthPassword.getResponse();
            return error;
        }
        if (Regex.passwordErrorSpecialCharacter.getMatcher(password) == null){
            error += Response.noSpecialCharacterPassword.getResponse() + "\n";
        }
        if (Regex.passwordErrorUpperCaseLetter.getMatcher(password) == null){
            error += Response.noUpperCasePassword.getResponse() + "\n";
        }
        if (Regex.passwordErrorLowerCase.getMatcher(password) == null){
            error += Response.noLowerCasePassword.getResponse() + "\n";
        }
        return error + "end of errors";
    }

    public static String checkEmail(String email){
        if (Regex.email.getMatcher(email) != null){
            for (int i = 0 ; i < User.getAllUsers().size() ; i++){
                if (User.getAllUsers().get(i).getEmail().toUpperCase().equals(email.toUpperCase())){
                    return Response.emailAlreadyExist.getResponse();
                }
            }
            return null;
        }
        return Response.invalidEmail.getResponse();
    }
    public String getEmail() {
        return email;
    }
    public static void printRecoveryQuestions(){
        System.out.println("0 :" + securityQuestions[0] + "\n"
                + "1 :" + securityQuestions[1] + "\n"
                + "2 :" +securityQuestions[2]);
    }
    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static String[] getSecurityQuestions(){
        return securityQuestions;
    }
    public int getRecoveryQuestion() {
        return recoveryQuestion;
    }
    public static User getUserByUsername(String username) {
        for (User user : allUsers)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }
    public String getSlogan() {
        return slogan;
    }
    public void setSecurityQuestion(int i , String answer){
        recoveryQuestion = i;
        recoveryAnswer = answer;
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
    public static void setLoggedInUser(User loggedInUser) {
        User.loggedInUser = loggedInUser;
    }
}
