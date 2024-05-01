package zombieapocalypse.game;

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

    public int readInt(String prompt) {
        System.out.print(prompt);
        int value = scanner.nextInt();
        scanner.nextLine();
        return value;
    }

    public void close() {
        scanner.close();
    }

}
