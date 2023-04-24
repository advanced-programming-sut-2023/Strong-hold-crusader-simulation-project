package org.example.view;

import org.example.controller.ProfileMenuController;
import org.example.view.commands.ProfileMenuCommands;
import org.example.view.commands.ProfileMenuResponds;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu extends Menu {
    public ProfileMenu(Scanner scanner) {
        super(scanner);
    }
    Matcher matcher;
    String input;
    @Override
    public void run() {
        while (true){
            input=scanner.nextLine();
            if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.CHANGE_USERNAME))!=null){
                System.out.println(ProfileMenuController.changeUsername(matcher));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.CHANGE_PASSWORD))!=null){
                System.out.println(ProfileMenuController.changePassword(matcher,scanner));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.CHANGE_NICKNAME))!=null){
                System.out.println(ProfileMenuController.changeNickname(matcher));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.CHANGE_EMAIL))!=null){
                System.out.println(ProfileMenuController.changeEmail(matcher));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.CHANGE_SLOGAN))!=null){
                System.out.println(ProfileMenuController.changeSlogan(matcher));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.REMOVE_SLOGAN))!=null){
                System.out.println(ProfileMenuController.removeSlogan(matcher));
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.DISPLAY_HIGHSCORE))!=null){
                System.out.println(ProfileMenuController.displayHighScore());
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.DISPLAY_RANK))!=null){
                System.out.println(ProfileMenuController.displayRank());
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.DISPLAY_SLOGAN))!=null){
                System.out.println(ProfileMenuController.displaySlogan());
            }
            else if ((matcher= ProfileMenuCommands.getMatcher(input,ProfileMenuCommands.DISPLAY_PROFILE))!=null){
                System.out.println(ProfileMenuController.displayAll());
            }
            else {
                System.out.println(ProfileMenuResponds.INVALID.getText());
            }
        }
    }
}
