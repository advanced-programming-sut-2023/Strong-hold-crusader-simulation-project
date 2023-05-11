package org.example.Test;

import org.example.controller.SignupMenuController;

public class test {
    public boolean password(){
        String s1 = "Password";
        return SignupMenuController.checkPasswordFormat(s1);
    }
}
