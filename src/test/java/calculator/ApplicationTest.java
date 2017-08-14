package calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Test
    public void testUserInputValid_Success() throws Exception{
        String[] userInput = new String[]{"one", "add", "two", "over", "three", "minus", "four", "times", "five"};
        boolean actualValue = Application.userInputValid(userInput);
        assertEquals(true, actualValue);
    }

    @Test
    public void testUserInputValid_EmptyInput() throws Exception{
        String[] userInput = new String[]{"one", "add", "two", "over", "three", "minus", "four", "times", "five"};
        boolean actualValue = Application.userInputValid(userInput);
        assertEquals(true, actualValue);
    }

    @Test
    public void testUserInputValid_WrongNumberOfWords() throws Exception{
        String[] userInput = new String[]{"one", "add", "two", "over"};
        boolean actualValue = Application.userInputValid(userInput);
        assertEquals(false, actualValue);
    }

    @Test
    public void testUserInputValid_InvalidNumber() throws Exception{
        String[] userInput = new String[]{"one", "add", "test"};
        boolean actualValue = Application.userInputValid(userInput);
        assertEquals(false, actualValue);
    }

    @Test
    public void testprocessInputValues_SingleValue() throws Exception{
        String[] userInput = new String[]{"one"};
        Double expectedValue = 1.0;
        Double actualValue = Application.processInputValues(userInput);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testUserInputValid_SingleOperations() throws Exception{
        String[] addInput = new String[]{"one", "add", "two"};
        Double expectedValue = 3.0;
        Double actualValue = Application.processInputValues(addInput);
        assertEquals(expectedValue, actualValue);

        String[] subInput = new String[]{"five", "minus", "seven"};
        expectedValue = -2.0;
        actualValue = Application.processInputValues(subInput);
        assertEquals(expectedValue, actualValue);

        String[] mulInput = new String[]{"eight", "times", "nine"};
        expectedValue = 72.0;
        actualValue = Application.processInputValues(mulInput);
        assertEquals(expectedValue, actualValue);

        String[] divInput = new String[]{"nine", "over", "two"};
        expectedValue = 4.5;
        actualValue = Application.processInputValues(divInput);
        assertEquals(expectedValue, actualValue);
    }

    @Test
    public void testUserInputValid_MultipleOperations() throws Exception{
        String[] userInput = new String[]{"one", "add", "two", "times", "three", "minus", "four", "over", "five"};
        Double expectedValue = 6.2;
        Double actualValue = Application.processInputValues(userInput);
        assertEquals(expectedValue, actualValue);
    }
}
