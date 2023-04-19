package view;

import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class Menu {
    public abstract void run();
    protected Scanner scanner;
    protected String input;
    protected Matcher matcher;
    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }
}
