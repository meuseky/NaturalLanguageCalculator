package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Application {

    private static final Map<String, Double> numberMap = createNumberMap();
    private static Map<String, Double> createNumberMap() {
        Map<String, Double> numberMap = new HashMap<>();
        numberMap.put("zero", 0.0);
        numberMap.put("one", 1.0);
        numberMap.put("two", 2.0);
        numberMap.put("three", 3.0);
        numberMap.put("four", 4.0);
        numberMap.put("five", 5.0);
        numberMap.put("six", 6.0);
        numberMap.put("seven", 7.0);
        numberMap.put("eight", 8.0);
        numberMap.put("nine", 9.0);
        return Collections.unmodifiableMap(numberMap);
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        boolean running = true;
        while(running) {
            String[] userInput = getUserInput(reader);

            if (userInputValid(userInput)) {
                processUserInput(userInput);
            } else {
                System.out.println("Invalid input. Exiting program.");
                running = false;
            }
        }
    }

    public static String[] getUserInput(BufferedReader reader) {
        System.out.println("Please enter a calculation: ");
        try {
            String input = reader.readLine().toLowerCase();
            return input.split("\\s+");
        } catch (IOException e) {
            return new String[0];
        }
    }

    public static boolean userInputValid(String[] userInput) {
        // An even number tokens means the statement is invalid
        if (userInput.length % 2 == 0) {
            return false;
        }

        List valid_operarions = Arrays.asList("add", "sub", "mul", "div");

        for (int i=0; i < userInput.length; i++) {
            if (i % 2 == 0) {
                if (!numberMap.containsKey(userInput[i]))
                    return false;
            } else {
                if (!valid_operarions.contains(userInput[i]))
                    return false;
            }
        }
        return true;
    }

    public static void processUserInput(String[] userInput) {
        Stack stackOne = new Stack();
        Stack steckTwo = new Stack();

        for (int i=0; i < userInput.length; i++) {
            if (i % 2 == 0) {
                stackOne.push(numberMap.get(userInput[i]));
            } else {
                stackOne.push(userInput[i]);
            }
        }

        while (stackOne.size() > 2) {
            Double number = (Double) stackOne.pop();
            String operation = (String) stackOne.pop();

            if (operation.equals("mul")) {
                Double second = (Double) stackOne.pop();
                stackOne.push(second * number);

            } else if (operation.equals("div")) {
                Double second = (Double) stackOne.pop();
                stackOne.push(second / number);
            } else {
                steckTwo.push(number);
                steckTwo.push(operation);
            }
        }
        steckTwo.push(stackOne.pop());

        while (steckTwo.size() > 2) {
            Double number = (Double) steckTwo.pop();
            String operation = (String) steckTwo.pop();

            if (operation.equals("add")) {
                Double second = (Double) steckTwo.pop();
                steckTwo.push(second + number);

            } else {
                Double second = (Double) steckTwo.pop();
                steckTwo.push(second - number);
            }

            stackOne.push(number);
            stackOne.push(operation);
        }
        stackOne.push(steckTwo.pop());

        System.out.println(stackOne.pop());
        return;
    }
}
