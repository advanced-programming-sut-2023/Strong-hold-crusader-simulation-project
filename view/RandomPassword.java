package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
public class RandomPassword {
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
