package part3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("calc> ");
            String input = sc.nextLine();

            Parser parser = new Parser(input);
            int result = parser.expr();
            System.out.println(result);
        }
    }
}
