package org.example.controller;

import org.example.model.InputOut.Regex;
import org.example.model.InputOut.Response;
import org.example.model.User;
import org.example.view.RandomPassword;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenuController extends Controller {
    public static String createUser(Matcher matcher , Scanner scanner) {
        matcher.find();
        String username = deleteQuot(matcher.group("username"));
        String password = deleteQuot(matcher.group("password"));
        String confirm = deleteQuot(matcher.group("confirm"));
        String email = deleteQuot(matcher.group("email"));
        String nickname = null;
        String slogan = null;
        if (matcher.group("nickname") != null){
            nickname = deleteQuot(matcher.group("nickname"));
        }
        if (matcher.group("slogan") != null){
            slogan = deleteQuot(matcher.group("slogan"));
            if (slogan.equals("random")){
                slogan = randomSlogan();
                System.out.println("your random slogan is :\n" + slogan);
            }
        }
        if (password.equals("random")){
            password = confirm = randomPassword(password , scanner);
            if (password.equals(Response.youAreInSignupMenu.getResponse())){
                return password;
            }
        }
        if (isThereEmptyField(username , password , email , slogan , slogan) != null){
            return Response.emptyField.getResponse();
        }
        if (User.checkEmail(email) != null){
            return User.checkEmail(email);
        }
        if (User.checkPassword(password , confirm) != null){
            return User.checkPassword(password , confirm);
        }
        if (Regex.username.getMatcher(username) == null){
            return Response.invalidUsername.getResponse();
        }
        username = User.UsernameCheck(username , scanner);
        if (username.equals(Response.youAreInSignupMenu.getResponse())) {
            return username;
        }
        return makeUser(username , password , email , nickname , slogan , scanner);
    }
    private static String makeUser
            (String username , String password , String email , String nickname , String slogan , Scanner scanner)
    {
        System.out.println(Response.pickYourSecurityQuestion.getResponse());
        User.printRecoveryQuestions();
        String command;
        while (true){
            command = scanner.nextLine();
            Matcher matcher = Regex.pickRecoveryQuestion.getMatcher(command);
            if (matcher == null){
                System.out.println(Response.pickQuestionFormat.getResponse());
                continue;
            }
            else {
                matcher.find();
                int x = Integer.parseInt(matcher.group("question"));
                if (x > 2 || x < 0){
                    System.out.println("question number must be 0, 1 or 2 ; please try again");
                    continue;
                }
                else {
                    if (matcher.group("answer").length() == 0){
                        System.out.println(Response.emptyField.getResponse());
                        continue;
                    }
                    if (!matcher.group("answer").equals(matcher.group("confirm"))){
                        System.out.println("answer and confirm doesn't match");
                        continue;
                    }
                    else {
                        User user = new User(username, password, email, nickname, slogan);
                        user.setSecurityQuestion(x , matcher.group("answer"));
                        break;
                    }
                }
            }
        }
        return Response.createUserSuccess.getResponse();
    }

    private static String deleteQuot(String input){
        Matcher matcher = Regex.doubleQuot.getMatcher(input);
        if(matcher != null){
            matcher.find();
            input = matcher.group("data");
        }
        return input;
    }
    public static String isThereEmptyField(String username , String password , String email , String nickname , String slogan) {
        if (password.length() == 0 || username.length() == 0 || email.length() == 0 ||
                (nickname != null && nickname.length() == 0) || (slogan != null && slogan.length() == 0)){
            return Response.emptyField.getResponse();
        }
        return null;
    }
    public static String randomPassword(String password , Scanner scanner) {
        password = RandomPassword.generatePass();
        System.out.println("your random password is :" + password);
        System.out.println("please reEnter your password to confirm it!");
        if (!scanner.nextLine().equals(password)){
            System.out.println(Response.passwordDifferentWithConfirm.getResponse());
            return Response.youAreInSignupMenu.getResponse();
        }
        return password;
    }
    public static String randomSlogan() {
        Random random = new Random();
        String[] randomSlogans = {"in rahi ke taze pato gozashti toosh ma thesh ridim" ,
                "zamin gerde manam ke kine e" ,
                "to ke ba ma hal nemikoni , la aghal pahato bede bala ma bahat hal konim",
                "hagho velesh adab fadash" ,
                "oon moghe ke to be lahaf toshak migofti lash toshak man lam toshak bood"};
        return randomSlogans[((random.nextInt()%100)+100)%randomSlogans.length];
    }
}
