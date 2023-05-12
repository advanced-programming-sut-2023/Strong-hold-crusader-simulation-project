package org.example.model;

import org.example.view.commands.SignupMenuCommands;
import org.example.view.commands.SignupMenuResponds;
import org.example.view.commands.ProfileMenuCommands;
import org.example.view.commands.ProfileMenuResponds;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class User {
    private static final String[] securityQuestions = {
            "What is my father's name?",
            "What was my first pet's name?",
            "What is my mother's last name?"
    };
    private static User loggedInUser;
    private static boolean stayLoggedIn = false;
    private static boolean exiting = false;

    private static ArrayList<User> allUsers = new ArrayList<>();
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String slogan = null;
    private int highScore;
    private int rank;
    private int recoveryQuestion;
    private String recoveryAnswer;
    public User(String  username, String password, String email, String nickname, String slogan) {
        this.username = username;
        this.password = getSha256(password);
        this.email = email;
        this.nickname = nickname;
        this.slogan = slogan;
        allUsers.add(this);
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
    public static boolean isExiting() {
        return exiting;
    }

    public static void setExiting(boolean exiting) {
        User.exiting = exiting;
    }
    private String getPassword() {
        return this.password;
    }
    public static String usernameCheck(String username , Scanner scanner){
        String newUsername;
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            if (User.getAllUsers().get(i).getUsername().equals(username)){
                System.out.println(SignupMenuResponds.usernameAlreadyExist.getResponse());
                newUsername = randomUsername(username);
                System.out.println("your new username is :" + newUsername);
                System.out.println("if you agree enter an \"y\" to continue else enter any character to exist.");
                if (!scanner.nextLine().equals("y")){
                    return SignupMenuResponds.youAreInSignupMenu.getResponse();
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
    public static String[] getSecurityQuestions(){
        return securityQuestions;
    }
    public static void printRecoveryQuestions(){
        System.out.println("0: " + securityQuestions[0] + "\n"
                + "1: " + securityQuestions[1] + "\n"
                + "2: " +securityQuestions[2]);
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
    public void setSecurityQuestion(int i , String answer){
        recoveryQuestion = i;
        recoveryAnswer = answer;
    }

    public void setPassword(String password) {
        this.password = getSha256(password);
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
                if (User.getUserByUsername(username)==null){
                    return null;
                }
                else {
                    return "this username " + ProfileMenuResponds.ALREADY_EXITS.getText();
                }
            }
            else {
                return "username " + ProfileMenuResponds.INVALID_FORMAT.getText();
            }
        }
        else {
            return "username " + ProfileMenuResponds.EMPTY_FIELD.getText();
        }
    }

    public static String checkPassword(String password , String confirm){
        if (!password.equals(confirm)){
            return SignupMenuResponds.passwordDifferentWithConfirm.getResponse();
        }
        String error = "password errors :\n";
        if (SignupMenuCommands.password.getMatcher(password) != null){
            return null;
        }
        else if (SignupMenuCommands.passwordErrorNumber.getMatcher(password) == null){
            error += SignupMenuResponds.noNumberPassword.getResponse() + "\n";
        }
        else if (SignupMenuCommands.passwordInvalidLength.getMatcher(password) != null){
            error += SignupMenuResponds.inValidLengthPassword.getResponse();
            return error;
        }
        if (SignupMenuCommands.passwordErrorSpecialCharacter.getMatcher(password) == null){
            error += SignupMenuResponds.noSpecialCharacterPassword.getResponse() + "\n";
        }
        if (SignupMenuCommands.passwordErrorUpperCaseLetter.getMatcher(password) == null){
            error += SignupMenuResponds.noUpperCasePassword.getResponse() + "\n";
        }
        if (SignupMenuCommands.passwordErrorLowerCase.getMatcher(password) == null){
            error += SignupMenuResponds.noLowerCasePassword.getResponse() + "\n";
        }
        return error + "end of errors";
    }
    public static String checkPasswordFormat(String newPassword) {
        if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_SPACE)==null){
            return "password " + ProfileMenuResponds.INVALID_FORMAT.getText();
        }
        if (ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_TOTAL)==null){
            String result="password errors:\n";
            if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_NUMBER)==null){
                result+=ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 8 characters\n";
            }
            if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_CAPITAL)==null){
                result+= ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 capital letter\n";
            }
            if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_SMALL)==null){
                result+= ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 small letter\n";
            }
            if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_DIGITS)==null){
                result+= ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 digit\n";
            }
            if(ProfileMenuCommands.getMatcher(newPassword,ProfileMenuCommands.PASSWORD_OTHER)==null){
                result+= ProfileMenuResponds.WEAK_PASSWORD.getText() + "enter at least 1 special character\n";
            }
            return result;
        }
        else if (User.getLoggedInUser().isPasswordCorrect(newPassword)){
            return ProfileMenuResponds.SAME.getText()+"password";
        }
        else{
            return null;
        }
    }
    public static boolean isEmailDuplicate(String email){
        for (User user : allUsers) {
            if (Objects.equals(user.getEmail().toLowerCase(), email)){
                return true;
            }
        }
        return false;
    }
    public static String checkEmail(String email){
        if (SignupMenuCommands.email.getMatcher(email) != null){
            for (int i = 0 ; i < User.getAllUsers().size() ; i++){
                if (User.getAllUsers().get(i).getEmail().equalsIgnoreCase(email)){
                    return SignupMenuResponds.emailAlreadyExist.getResponse();
                }
            }
            return null;
        }
        return SignupMenuResponds.invalidEmail.getResponse();
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
            return "email "+ ProfileMenuResponds.INVALID_FORMAT.getText();
        }
    }
    public static User getLoggedInUser() {
        return loggedInUser;
    }
    public static String getSha256(String value) {
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(value.getBytes());
            return bytesToHex(md.digest());
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    private static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        return result.toString();
    }
}
