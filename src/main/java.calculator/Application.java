package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Application {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean running = true;

        do {
         String userInput = getUserInput(reader);
         running = processUserInput(userInput);
        } while(running);
    }

    public static String getUserInput(BufferedReader reader) {
        System.out.println("Please enter a calculation: ");
        try {
            return reader.readLine();
        } catch (IOException e) {
            return "";
        }
    }

    public static boolean processUserInput(String userInput) {
        Map<String, Integer> numberMap = new HashMap<>();
        numberMap.put("zero", 0);
        numberMap.put("one", 1);
        numberMap.put("two", 2);
        numberMap.put("three", 3);
        numberMap.put("four", 4);
        numberMap.put("five", 5);
        numberMap.put("six", 6);
        numberMap.put("seven", 7);
        numberMap.put("eight", 8);
        numberMap.put("nine", 9);

        // Issue multiple alias' for same command
        Map<String, Runnable> operationMap = new HashMap<>();
        operationMap.put("add", () -> System.out.println("cat"));
        operationMap.put("subtract,", () -> System.out.println("hat"));
        operationMap.put("times", () -> System.out.println("bat"));
        operationMap.put("over", () -> System.out.println("tat"));

        String[] tokens = userInput.split("\\s+");
        // maybe convert to upper case here

        // An even number tokens means the statement is invalid
        if (tokens.length % 2 == 0) {
            return false;
        }

        Stack stack = new Stack();

        // Check if each token is valid
        for (int i=0; i < tokens.length; i++) {
            if (i % 2 == 0) {
                // check if token is a number
                Integer number = numberMap.get(tokens[i]);
                stack.push(number);
            } else {
                // check if token is a command
                Runnable operation = operationMap.get(tokens[i]);
                stack.push(operation);
            }
        }

        return true;
    }
}
