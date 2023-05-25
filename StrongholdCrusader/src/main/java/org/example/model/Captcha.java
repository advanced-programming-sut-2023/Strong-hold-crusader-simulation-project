package org.example.model;

import org.example.view.commands.SignupMenuResponds;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Captcha {
    private static ArrayList<String> numbers=new ArrayList<>();
    private static void initialize (){
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |   |  \n" +
                "  | | |  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___     \n" +
                "  |_  |    \n" +
                "   _| |_   \n" +
                "  |_____|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |_  |  \n" +
                "  |  _|  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |_  |  \n" +
                "  |_  |  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  | | |  \n" +
                "  |_  |  \n" +
                "    |_|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |  _|  \n" +
                "  |_  |  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |  _|  \n" +
                "  | . |  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  |_  |  \n" +
                "    | |  \n" +
                "    |_|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  | . |  \n" +
                "  | . |  \n" +
                "  |___|  \n" );
        numbers.add("---------\n" +
                "   ___   \n" +
                "  | . |  \n" +
                "  |_  |  \n" +
                "  |___|  \n");
    }
    private static int generateRandom(){
        Random random=new Random();
        int number=4;
        int captcha=0;
        for (int i=0;i < number;i++){
            captcha*=10;
            captcha+=random.nextInt(10);
        }
        if (captcha < 1000){
            return generateRandom();
        }
        return captcha;
    }
    public static int noiser(int z){
        Random random = new Random();
        initialize();
        ArrayList<Integer> noise = new ArrayList<>();
        while (true){
            if (noise.size() >= 6){
                break;
            }
            int temp = random.nextInt();
            temp = Math.abs(temp);
            temp %= numbers.get(z).length();
            if (numbers.get(z).charAt(temp) == ' '){
                noise.add(temp);
            }
        }
        for (int i= 0 ; i < numbers.get(z).length() ; i++){
            if (noise.contains(i)){
                System.out.print('*');
            }
            else {
                System.out.print(numbers.get(z).charAt(i));
            }
        }
        return 0;
    }
    public static int[] getNumbers(int x){
        int[] result = new int[4];
        result[3] = x%10; x /= 10;
        result[2] = x%10; x /= 10;
        result[1] = x%10; x /= 10;
        result[0] = x%10;
        return result;
    }
    public static int makeCaptcha(Scanner scanner){
        initialize();
        while (true){
            int capcha = generateRandom();
            int[] Numbers = getNumbers(capcha);
            noiser(Numbers[0]);
            noiser(Numbers[1]);
            noiser(Numbers[2]);
            noiser(Numbers[3]);
            return capcha;
        }
    }
    public static boolean run(Scanner scanner){
//        System.out.println("please confirm you are not a robot with entering this captcha .");
//        while (true){
//            int z = makeCaptcha(scanner);
//            while (true) {
//                String input = scanner.nextLine();
//                if (Pattern.matches("(recaptcha|exit|\\d+)" , input)) {
//                    if (input.equals("recaptcha")) {
//                        break;
//                    }
//                    if (input.equals("exit")) {
//                        return false;
//                    } else if (Integer.parseInt(input) == z) {
//                        System.out.println("congrats");
//                        return true;
//                    } else {
//                        System.out.println("wrong answer!!!");
//                    }
//                }
//                else
//                    System.out.println("you can either enter \"recaptcha\" , \"exit\" , or the captcha number.");
//            }
//        }
        return true;
    }
}