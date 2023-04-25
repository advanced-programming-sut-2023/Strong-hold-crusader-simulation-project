package org.example.controller;

import org.example.view.commands.SignupMenuCommands;
import org.example.view.commands.SignupMenuResponds;
import org.example.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;

public class SignupMenuController extends Controller {
    public static String createUser(Matcher matcher , Scanner scanner) {
        matcher.find();
        String username = deleteQuote(matcher.group("username"));
        String password = deleteQuote(matcher.group("password"));
        String confirm = deleteQuote(matcher.group("confirm"));
        String email = deleteQuote(matcher.group("email"));
        String nickname = null;
        String slogan = null;
        if (matcher.group("nickname") != null){
            nickname = deleteQuote(matcher.group("nickname"));
        }
        if (matcher.group("slogan") != null){
            slogan = deleteQuote(matcher.group("slogan"));
            if (slogan.equals("random")){
                slogan = randomSlogan();
                System.out.println("your random slogan is :\n" + slogan);
            }
        }
        if (password.equals("random")){
            password = confirm = randomPassword(password , scanner);
            if (password.equals(SignupMenuResponds.youAreInSignupMenu.getResponse())){
                return password;
            }
        }
        if (isThereEmptyField(username , password , email , slogan , slogan) != null){
            return SignupMenuResponds.emptyField.getResponse();
        }
        if (User.checkEmail(email) != null){
            return User.checkEmail(email);
        }
        if (User.checkPassword(password , confirm) != null){
            return User.checkPassword(password , confirm);
        }
        if (SignupMenuCommands.username.getMatcher(username) == null){
            return SignupMenuResponds.invalidUsername.getResponse();
        }
        username = User.usernameCheck(username , scanner);
        if (username.equals(SignupMenuResponds.youAreInSignupMenu.getResponse())) {
            return username;
        }
        return makeUser(username , password , email , nickname , slogan , scanner);
    }
    private static String makeUser
            (String username , String password , String email , String nickname , String slogan , Scanner scanner)
    {
        System.out.println(SignupMenuResponds.pickYourSecurityQuestion.getResponse());
        User.printRecoveryQuestions();
        String command;
        while (true){
            command = scanner.nextLine();
            Matcher matcher = SignupMenuCommands.pickRecoveryQuestion.getMatcher(command);
            if (matcher == null){
                System.out.println(SignupMenuResponds.pickQuestionFormat.getResponse());
                continue;
            }
            matcher.find();
            int x = Integer.parseInt(matcher.group("question"));
            if (x > 2 || x < 0){
                System.out.println("question number must be 0, 1 or 2 ; please try again");
                continue;
            }
            if (matcher.group("answer").length() == 0){
                System.out.println(SignupMenuResponds.emptyField.getResponse());
                continue;
            }
            if (!matcher.group("answer").equals(matcher.group("confirm"))){
                System.out.println("answer and confirm doesn't match");
                continue;
            }
            User user = new User(username, password, email, nickname, slogan);
            user.setSecurityQuestion(x , matcher.group("answer"));
            break;
        }
        return SignupMenuResponds.createUserSuccess.getResponse();
    }

    private static String deleteQuote(String input){
        Matcher matcher = SignupMenuCommands.doubleQuot.getMatcher(input);
        if(matcher != null){
            matcher.find();
            input = matcher.group("data");
        }
        return input;
    }
    public static String isThereEmptyField(String username , String password , String email , String nickname , String slogan) {
        if (password.length() == 0 || username.length() == 0 || email.length() == 0 ||
                (nickname != null && nickname.length() == 0) || (slogan != null && slogan.length() == 0)){
            return SignupMenuResponds.emptyField.getResponse();
        }
        return null;
    }
    public static String randomPassword(String password , Scanner scanner) {
        password = SignupMenuController.generatePass();
        System.out.println("your random password is :" + password);
        System.out.println("please reEnter your password to confirm it!");
        if (!scanner.nextLine().equals(password)){
            System.out.println(SignupMenuResponds.passwordDifferentWithConfirm.getResponse());
            return SignupMenuResponds.youAreInSignupMenu.getResponse();
        }
        return password;
    }
    public static String randomSlogan() {
        Random random = new Random();
        String[] randomSlogans = {
                "in rahi ke taze pato gozashti toosh ma thesh ridim",
                "I shall have my revenge, in this life or the next",
                "zamin gerde manam ke kine e",
                "hagho velesh adab fadash",
                "oon moghe ke to be lahaf toshak migofti lash toshak man lam toshak bood"
        };
        return randomSlogans[((random.nextInt()%100)+100)%randomSlogans.length];
    }
    public static String generatePass() {
        Random random = new Random();
        String ss = "QWERTYUIOPASDFGHJKLZXCVBNM";
        String ls = "qwertyuioplkjhgfdsazxcvbnm";
        String sp = "!@#$%^&*";
        String nm = "1234567890";
        String fn = ss + ls + sp + nm;
        ArrayList<Character> source = new ArrayList<>();
        source.add(ss.charAt(Math.abs(random.nextInt()) % 26));
        source.add(ls.charAt(Math.abs(random.nextInt()) % 26));
        source.add(sp.charAt(Math.abs(random.nextInt()) % 7));
        source.add(nm.charAt(Math.abs(random.nextInt()) % 10));
        source.add(fn.charAt(Math.abs(random.nextInt()) % 69));
        source.add(fn.charAt(Math.abs(random.nextInt()) / 2 % 69));
        Collections.shuffle(source, random);
        String result = "";
        for (int i = 0; i < 6; i++) {
            result += source.get(i);
        }
        return result;
    }
}
