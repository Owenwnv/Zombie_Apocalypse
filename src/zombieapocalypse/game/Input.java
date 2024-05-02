package zombieapocalypse.game;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

    private Scanner scanner;

    public Input() {
        this.scanner = new Scanner(System.in);
    }

    public String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int readInt(int min, int max) {
        while (true) {
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                if (value >= min && value <= max) {
                    System.out.println("");
                    return value;
                } else {
                    System.out.print("Enter a number between " + min + " and " + max + ".\n");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public int readIntPrompt(String prompt, int min, int max) {
        System.out.print(prompt);
        return readInt(min, max);
    }

    public int readYesNo(String prompt) {
        System.out.print(prompt);
        System.out.print("0. No\n");
        System.out.print("1. Yes\n");
        return readInt(0, 1);
    }

    public void close() {
        scanner.close();
    }
}
