package calculator;

public class Application {

    public static void main(String[] args) {
        Console console = System.console();

        boolean running = true;
        while(running) {
            String calculation = console.readLine("Please enter a calculation: ");
        }
    }
}
