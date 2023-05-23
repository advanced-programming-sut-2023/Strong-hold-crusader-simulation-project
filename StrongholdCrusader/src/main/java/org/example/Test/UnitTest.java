package org.example.Test;

import org.example.controller.SignupMenuController;

import org.example.view.commands.SignupMenuCommands;
import org.example.view.commands.SignupMenuResponds;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.example.model.Captcha;
import org.example.model.User;
import java.util.Scanner;

class UnitTest{
    @Test
    public void test1(){
        String passwordErors1 = "password errors :\n";
        passwordErors1 += SignupMenuResponds.noNumberPassword.getResponse() + "\n";
        passwordErors1 += SignupMenuResponds.noSpecialCharacterPassword.getResponse() + "\n";
        passwordErors1 += SignupMenuResponds.noUpperCasePassword.getResponse() + "\n";
        passwordErors1 += "end of errors";
        Assertions.assertEquals(passwordErors1 , User.checkPassword("password" , "password"));
    }
    @Test
    public void test2(){
        String passwordErors1 = "password errors :\n";
        passwordErors1 += SignupMenuResponds.noNumberPassword.getResponse() + "\n";
        passwordErors1 += SignupMenuResponds.noSpecialCharacterPassword.getResponse() + "\n";
        passwordErors1 += "end of errors";
        Assertions.assertEquals(passwordErors1 , User.checkPassword("Password", "Password"));
    }
    @Test
    public void test3(){
        String passwordErors1 = "password errors :\n";
        passwordErors1 += SignupMenuResponds.noUpperCasePassword.getResponse() + "\n";
        passwordErors1 += "end of errors";
        Assertions.assertEquals(passwordErors1 , User.checkPassword("password1*" , "password1*"));
//
//        Assertions.assertEquals(false , User.checkPassword("Password1*" , "Password1*"));
    }
    @Test
    public void test4(){
        String passwordErors1 = "password errors :\n";
        passwordErors1 += SignupMenuResponds.inValidLengthPassword.getResponse() ;
        Assertions.assertEquals(passwordErors1 , User.checkPassword("Pa2*" , "Pa2*" ));
    }
    @Test
    public void test5(){
        String passwordErrors1 = SignupMenuResponds.passwordDifferentWithConfirm.getResponse();
        Assertions.assertEquals(passwordErrors1 , User.checkPassword("pass" , "confirm"));
    }
    @Test
    public void test6(){
        Assertions.assertEquals(SignupMenuResponds.invalidEmail.getResponse() , User.checkEmail("amir"));
    }
    @Test
    public void test7(){
        Assertions.assertEquals(null , User.checkEmail("amir@gamil.com"));
    }
    @Test
    public void test8(){
        Assertions.assertEquals(null , User.checkEmail("amir@gamil.com.ali.reza.mahmood"));
    }
    @Test
    public void test9(){
        Assertions.assertEquals(SignupMenuResponds.invalidEmail.getResponse() , User.checkEmail("amir@amir@amir.com"));
    }
    @Test
    public void test10(){
        Assertions.assertEquals(false , Captcha.run(new Scanner("exit")));
    }
    @Test
    public void test11(){
        String in = "user create -u  -p password password -email amir@gmail.com";
        Assertions.assertEquals(SignupMenuResponds.emptyField.getResponse() ,
                SignupMenuController.createUser(SignupMenuCommands.createUser.getMatcher(in) , new Scanner(in)));
    }
    @Test
    public void test12(){
        String in = "user create -u amir! -p Password1* Password1* -email amir@gmail.com";
        Assertions.assertEquals(SignupMenuResponds.invalidUsername.getResponse() ,
                SignupMenuController.createUser(SignupMenuCommands.createUser.getMatcher(in) , new Scanner(in)));
    }
    @Test
    public void test13(){
        Assertions.assertEquals(SignupMenuResponds.youAreInSignupMenu.getResponse() ,
                SignupMenuController.randomPassword("" , new Scanner("random")));
    }
}