package org.example.model;


import java.io.*;
import java.net.URL;
import java.security.MessageDigest;
import java.util.*;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.view.Responds.*;
import org.example.view.Signup;

public class User implements Serializable {
    public static final String[] securityQuestions = {
            "What is my father's name?",
            "What was my first pet's name?",
            "What is my mother's last name?"
    };
    private static User loggedInUser;
    private static boolean stayLoggedIn = false;
    private static boolean exiting = false;
    private URL profilePicture = null;

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
        if (nickname.length() == 0){
            this.nickname = null;
        }
        this.slogan = slogan;
        if (slogan.length() == 0 ){
            this.slogan = null;
        }
        allUsers.add(this);
        highScore = 0;
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

    public static String getQuestion(String username) {
        for (int i = 0 ; i < allUsers.size() ; i++){
            if (allUsers.get(i).getUsername().equals(username)){
                return securityQuestions[allUsers.get(i).getRecoveryQuestion()];
            }
        }
        return "fail";
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    @Override
    public String toString() {
        String result = "Users :\n";
        result += ("name :" + this.getUsername() +"\n");
        result += ("password :" + this.getPassword() +"\n");
        result += ("nickname :" + this.getNickname() +"\n");
        result += ("slogan :" + this.getSlogan() + "\n");
        result += ("email :" + this.getEmail() + "\n");
        result += ("security question :" + this.recoveryQuestion + "\n");
        result += ("recovery answer :" + this.recoveryAnswer + "\n");
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
    public static String usernameCheck(TextField username){
        String newUsername;
        if (!Pattern.matches("[a-zA-Z0-9]+" , username.getText())) {
            return "fail";
        }
        return "success";
    }
    public static boolean doesUsernameExist(String username){
        for (int i = 0 ; i < User.getAllUsers().size() ; i++){
            if (User.getAllUsers().get(i).getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public static String randomUsername(String old){
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
        if (slogan.length() != 0){
            return slogan;
        }
        else {
            return null;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setSecurityQuestion(int i , String answer){
        recoveryQuestion = i;
        recoveryAnswer = answer;
        User.saveFile();
    }

    public void setPassword(String password) {
        this.password = getSha256(password);
        User.saveFile();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
        User.saveFile();
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
        User.saveFile();
    }

    public void removeSlogan() {
        this.slogan = null;
        User.saveFile();
    }
    public boolean isPasswordCorrect(String password) {
        return getSha256(password).equals(this.password);
    }

    public URL getProfilePicture() {
        if (this.profilePicture != null) {
            return this.profilePicture;
        }
        else {
            return (Signup.class.getResource("/Images/avatar0.png"));
        }
    }

    public void setProfilePicture(URL image){
        this.profilePicture = image;
        saveFile();
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

    public static String checkPassword(PasswordField password , PasswordField confirm){
        if (!password.getText().equals(confirm.getText())){
            Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
            errorAlert.setHeaderText("password error");
            errorAlert.setContentText(SignupMenuResponds.passwordDifferentWithConfirm.getResponse());
            errorAlert.showAndWait();
            return "fail";
        }
        String error = "";
        if (SignupMenuCommands.password.getMatcher(password.getText()) != null){
            return "success";
        }
        else if (SignupMenuCommands.passwordErrorNumber.getMatcher(password.getText()) == null){
            error += SignupMenuResponds.noNumberPassword.getResponse() + "\n";
        }
        else if (SignupMenuCommands.passwordInvalidLength.getMatcher(password.getText()) != null){
            error += SignupMenuResponds.inValidLengthPassword.getResponse();
            return error;
        }
        if (SignupMenuCommands.passwordErrorSpecialCharacter.getMatcher(password.getText()) == null){
            error += SignupMenuResponds.noSpecialCharacterPassword.getResponse() + "\n";
        }
        if (SignupMenuCommands.passwordErrorUpperCaseLetter.getMatcher(password.getText()) == null){
            error += SignupMenuResponds.noUpperCasePassword.getResponse() + "\n";
        }
        if (SignupMenuCommands.passwordErrorLowerCase.getMatcher(password.getText()) == null){
            error += SignupMenuResponds.noLowerCasePassword.getResponse() + "\n";
        }
        Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
        errorAlert.setHeaderText("invalid password");
        errorAlert.setContentText(error);
        errorAlert.showAndWait();
        return "fail";

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
    public int calculateRank() {
        for (int i = 0; i < allUsers.size() - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < allUsers.size() - i - 1; j++) {
                if (allUsers.get(i).getHighScore() > allUsers.get(j + 1).getHighScore())
                {
                    User temp = allUsers.get(j);
                    allUsers.set(j, allUsers.get(j + 1));
                    allUsers.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
        return allUsers.indexOf(this);
    }
    public static int getQuestionNumber(String question){
        for (int i = 0 ; i < securityQuestions.length ; i++ ){
            if (securityQuestions[i].equals(question)){
                return i;
            }
        }
        return -1;
    }
}
