package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Captcha {
    private static ArrayList<String> numbers=new ArrayList<>();
    private static void initialize (){
        numbers.add("     \n" +
                " ___ \n" +
                "|   |\n" +
                "| | |\n" +
                "|___|\n" +
                "     ");
        numbers.add("       \n" +
                " ___   \n" +
                "|_  |  \n" +
                " _| |_ \n" +
                "|_____|\n" +
                "       ");
        numbers.add("     \n" +
                " ___ \n" +
                "|_  |\n" +
                "|  _|\n" +
                "|___|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "|_  |\n" +
                "|_  |\n" +
                "|___|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "| | |\n" +
                "|_  |\n" +
                "  |_|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "|  _|\n" +
                "|_  |\n" +
                "|___|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "|  _|\n" +
                "| . |\n" +
                "|___|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "|_  |\n" +
                "  | |\n" +
                "  |_|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "| . |\n" +
                "| . |\n" +
                "|___|\n" +
                "     ");
        numbers.add("     \n" +
                " ___ \n" +
                "| . |\n" +
                "|_  |\n" +
                "|___|\n" +
                "     ");
    }
    private static int generateRandom(){
        Random random=new Random();
        int number=4;
        int captcha=0;
        number+=random.nextInt(5);
        for (int i=0;i < number;i++){
            captcha*=10;
            captcha+=random.nextInt(10);
        }
        return captcha;
    }
    public static String reverseIt(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);
        for (i = (len - 1); i >= 0; i--){
            dest.append(source.charAt(i));
        }
        return dest.toString();
    }
    private static void truth(int a){
        initialize();
        String index=Integer.toString(a);
        int b= Integer.parseInt(reverseIt(index));
        for (int i=0; i< index.length();i++){
            int mod = b%10;
            System.out.print(numbers.get(mod));
            b/=10;
        }
    }
    public static boolean captcha(){
        int number = generateRandom();
        Scanner scanner = new Scanner(System.in);
        truth(number);
        System.out.println("\nenter captcha");

        int enteredNumber=scanner.nextInt();
        return number == enteredNumber;
    }
}
