package part1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("calc> ");
        String input = sc.nextLine();

        Interpreter interpreter = new Interpreter(input);
        int result = interpreter.expr();
        System.out.println(result);
    }
}
