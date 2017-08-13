package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
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
        return numberMap;
    }

    private static final List<String> additionAliases = Arrays.asList("add", "plus");
    private static final List<String> subtractionAliases = Arrays.asList("subtract", "minus", "less");
    private static final List<String> multiplicationAliases = Arrays.asList("multiplied-by", "times");
    private static final List<String> divisionAliases = Arrays.asList("divided-by", "over");
    private static final List<String> validOperations = getValidOperations();
    private static List<String> getValidOperations() {
        List<String> operationList = new ArrayList<>();
        operationList.addAll(additionAliases);
        operationList.addAll(subtractionAliases);
        operationList.addAll(multiplicationAliases);
        operationList.addAll(divisionAliases);
        return operationList;
    }

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        DecimalFormat outputFormat = new DecimalFormat("0.##");

        boolean running = true;
        while(running) {
            String[] userInput = getUserInput(reader);

            if (userInputValid(userInput)) {
                Double outputNumber = processInputValues(userInput);
                System.out.println(outputFormat.format(outputNumber));
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

        for (int i=0; i < userInput.length; i++) {
            if (i % 2 == 0) {
                if (!numberMap.containsKey(userInput[i]))
                    return false;
            } else {
                if (!validOperations.contains(userInput[i]))
                    return false;
            }
        }
        return true;
    }

    public static Double processInputValues(String[] inputValues) {
        Stack stack = new Stack();

        for (int i=0; i < inputValues.length; i++) {
            if (i % 2 == 0) {
                stack.push(numberMap.get(inputValues[i]));
            } else {
                stack.push(inputValues[i]);
            }
        }

        stack = processMulAndDiv(stack);
        return processAddAndSub(stack);
    }

    public static Stack processMulAndDiv(Stack inputStack) {
        Stack outputStack = new Stack();

        while (inputStack.size() > 2) {
            Double number = (Double) inputStack.pop();
            String operation = (String) inputStack.pop();

            if (multiplicationAliases.contains(operation)) {
                Double additionalNumber = (Double) inputStack.pop();
                inputStack.push(additionalNumber * number);

            } else if (divisionAliases.contains(operation)) {
                Double additionalNumber = (Double) inputStack.pop();
                inputStack.push(additionalNumber / number);

            } else {
                outputStack.push(number);
                outputStack.push(operation);
            }
        }
        outputStack.push(inputStack.pop());

        return outputStack;
    }

    public static Double processAddAndSub(Stack inputStack) {
        Double resultValue = null;

        while (inputStack.size() > 2) {
            Double firstValue = (Double) inputStack.pop();
            String operation = (String) inputStack.pop();
            Double secondValue = (Double) inputStack.pop();

            if (additionAliases.contains(operation)) {
                resultValue = firstValue + secondValue;
            } else {
                resultValue = firstValue - secondValue;
            }
            inputStack.push(resultValue);
        }
        return (Double) inputStack.pop();
    }
}
